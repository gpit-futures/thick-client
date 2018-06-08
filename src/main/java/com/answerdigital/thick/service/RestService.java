package com.answerdigital.thick.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.answerdigital.thick.dto.Entry;
import com.answerdigital.thick.dto.ResponseDTO;
import com.answerdigital.thick.dto.SearchDTO;

public abstract class RestService<DTO extends ResponseDTO> {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${base.url}")
	private String baseUrl;
	
	@Value("${patient.namespace}")
	private String patientNamespace;
	
	@Value("${response.format}")
	private String responseFormat;
	
	@Value("${search.query}")
	private String searchQuery;
	
	private Class<DTO> clazz;
	
	public RestService(Class<DTO> clazz) {
		this.clazz = clazz;
	}
	
	public List<DTO> readAll() {
		List<DTO> dtos = new ArrayList<>();
		String url = MessageFormat.format("{0}{1}", baseUrl, clazz.getSimpleName());
		SearchDTO searchDto = restTemplate.getForObject(url, SearchDTO.class);
		
		if (searchDto.getEntry() != null) {
			for (Entry entry : searchDto.getEntry()) {
				dtos.add(restTemplate.getForObject(entry.getFullUrl(), clazz));
			}
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
