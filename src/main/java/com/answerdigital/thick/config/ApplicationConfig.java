package com.answerdigital.thick.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@PropertySources({
	@PropertySource(value="classpath:application-env.properties"),
	@PropertySource(value="file:${CONF_DIR}/app.properties", ignoreResourceNotFound=true) 
})
public class ApplicationConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

	    List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
	    messageConverters.add(mappingJackson2HttpMessageConverter());

	    return restTemplate;
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();    
	    ObjectMapper objectMapper = objectMapper();
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    List<MediaType> jacksonTypes = new ArrayList<>(converter.getSupportedMediaTypes());
	    jacksonTypes.add(new MediaType("application", "fhir+json"));

	    converter.setSupportedMediaTypes(jacksonTypes);
	    converter.setObjectMapper(objectMapper);
	    
	    return converter;
	}   
	
}
