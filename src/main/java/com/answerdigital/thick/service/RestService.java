package com.answerdigital.thick.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.answerdigital.pcaap.dto.Authentication;
import com.answerdigital.thick.dto.CorePatient;
import com.answerdigital.pcaap.dto.Entry;
import com.answerdigital.pcaap.dto.ResponseDTO;
import com.answerdigital.pcaap.dto.SearchDTO;
import com.answerdigital.thick.mapper.CorePatientToPatientPropertyMapper;

public abstract class RestService<DTO extends ResponseDTO> {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private CorePatientToPatientPropertyMapper mapper;
	
	@Value("${base.url}")
	private String baseUrl;
	
	@Value("${auth.url}")
	private String authUrl;
	
	@Value("${client.secret}")
	private String clientSecret;
	
	@Value("${client.id}")
	private String clientId;
	
	@Value("${patient.namespace}")
	private String patientNamespace;
	
	@Value("${response.format}")
	private String responseFormat;
	
	@Value("${search.query}")
	private String searchQuery;
	
	private Class<DTO> clazz;
	
	private String accessToken;
	
	public RestService(Class<DTO> clazz) {
		this.clazz = clazz;
	}
	
	@PostConstruct
	public void login() {
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Basic " + Base64Utils.encodeToString((clientId + "::" + clientSecret).getBytes()));
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", "john.smith1");
		params.add("password", "password");
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, header);
				
		ResponseEntity<Authentication> auth = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Authentication.class);
		accessToken = auth.getBody().getAccess_token();
		
		messageService.connectAndLogin(accessToken);
	}
	
	public List<DTO> readAll() {
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer " + accessToken);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(header);
		
		List<DTO> dtos = new ArrayList<>();
		ResponseEntity<CorePatient[]> response = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, CorePatient[].class);
		
			for (CorePatient patient : Arrays.asList(response.getBody())) {
				
				dtos.add((DTO) mapper.map(patient));
			}
		
		return dtos;
	}
	
	public DTO read(String id) {
		String url = MessageFormat.format("{0}{1}{2}{3}{4}", baseUrl, clazz.getSimpleName(), searchQuery, namespace(), id);
		SearchDTO searchDto = restTemplate.getForObject(url, SearchDTO.class);
		
		if (searchDto.getEntry() != null) {
			return restTemplate.getForObject(searchDto.getEntry()[0].getFullUrl(), clazz);
		} else {
			return null;
		}
	}

	public List<DTO> readAssociated(String type, String id) {
		List<DTO> dtos = new ArrayList<>();
		String url = MessageFormat.format("{0}{1}{2}{3}{4}", baseUrl, clazz.getSimpleName(), "?" + type.toLowerCase() + "=", id, "&_format=json");	
		SearchDTO searchDto = restTemplate.getForObject(url, SearchDTO.class);
		
		for (Entry entry : searchDto.getEntry()) {
			dtos.add((DTO)restTemplate.getForObject(entry.getFullUrl(), clazz));
		}
	
		return dtos;
	}
	
	public ResponseDTO create(DTO dto) throws Exception {
		dto.setId(null);
		return merge(dto);
	}
	
	public ResponseDTO update(DTO dto) throws Exception {
		return merge(dto);
	}
	
	private ResponseDTO merge(DTO dto) throws Exception {
		boolean isUpdate = dto.getId() != null;
		String id = dto.getIdentifier()[0].getValue();
		DTO originalDto = read(id);
		
		String url = MessageFormat.format("{0}{1}", baseUrl, clazz.getSimpleName());
		url = isUpdate? 
				MessageFormat.format("{0}{1}{2}{3}", url, "/", originalDto.getId(), responseFormat) : 
				MessageFormat.format("{0}{1}", url, responseFormat);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        ResponseEntity<ResponseDTO> response = null;
        
        try {
	        response = restTemplate.exchange(
	        		url, isUpdate ? HttpMethod.PUT : HttpMethod.POST, new HttpEntity<DTO>(dto, headers), ResponseDTO.class);
        } catch (RestClientException e) {
        	throw new Exception();
        }
        
        if (!(response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK)) {
        	throw new Exception();
        }    
        
        return response.getBody();
		
	}
	
	protected abstract String namespace();

}
