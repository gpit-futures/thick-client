package com.answerdigital.thick.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.answerdigital.thick.dto.Appointment;
import com.answerdigital.thick.dto.Slot;

@Service
public class AppointmentRestService extends RestService<Appointment> {

	@Value("${slot.namespace}")
	private String appointmentNamespace;

	@Autowired
	public AppointmentRestService() {
		super(Appointment.class);
	}

	@Override
	protected String namespace() {
		return appointmentNamespace;
	}
	
	public List<Slot> bookAppointment(String serverUrl) {
		
		String url = MessageFormat.format("{0}/fhir/Appointment", serverUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/fhir+json;charset=utf-8");	
//		headers.add("Content-Type","application/json+fhir;charset=utf-8");
		
		HttpEntity<String> request = new HttpEntity<>("{\"resourceType\":\"Appointment\",\"id\":\"3\",\"meta\":{\"versionId\":\"1531393422000\",\"lastUpdated\":\"2018-07-12T12:03:42.000+01:00\",\"profile\":[\"https://fhir.nhs.uk/STU3/StructureDefinition/GPConnect-Appointment-1\"]},\"identifier\":[{\"system\":\"https://fhir.nhs.uk/Id/gpconnect-appointment-identifier\",\"value\":\"3\"}],\"status\":\"booked\",\"appointmentType\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/ValueSet/c80-practice-codes\",\"code\":\"394592004\",\"display\":\"General Medical Practitioner\"}],\"text\":\"In-person\"},\"priority\":0,\"description\":\"test\",\"start\":\"2018-07-19T12:30:00.000+01:00\",\"end\":\"2018-07-19T12:40:00.000+01:00\",\"minutesDuration\":0,\"slot\":[{\"reference\":\"Slot/137693\"}],\"participant\":[{\"actor\":{\"reference\":\"Patient/2\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Location/1\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Practitioner/1\"},\"status\":\"accepted\"}]}", headers);
//		HttpEntity<String> request = new HttpEntity<String>("{\"resourceType\":\"Appointment\",\"status\":\"booked\",\"appointmentType\":{\"coding\":[{ \"system\":\"http://example.org/appointment-type\",\"code\":\"394592004\",\"display\":\"General Medical Practitioner\"}],\"text\":\"In-person\"},\"start\": \"2018-07-14T11:20:01.000Z\",\"end\":\"2018-07-14T11:30:01.000Z\",\"slot\":[{\"reference\":\"Slot/133564\"}],\"participant\":[{\"actor\":{\"reference\":\"Patient/2\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Practitioner/1\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Location/1\"},\"status\":\"accepted\"}],\"description\":\"grrrr\"}", headers);
		
//		HttpEntity<String> request = new HttpEntity<String>("{\"resourceType\":\"Appointment\",\"status\":\"booked\",\"appointmentType\":{\"coding\":[{ \"system\":\"http://example.org/appointment-type\",\"code\":\"394592004\",\"display\":\"General Medical Practitioner\"}],\"text\":\"In-person\"},\"start\": \"2018-07-09T09:30:00.000Z\",\"end\":\"2018-07-09T09:40:00.000Z\",\"slot\":[{\"reference\":\"Slot/8369\"}],\"participant\":[{\"actor\":{\"reference\":\"Patient/3\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Practitioner/1\"},\"status\":\"accepted\"},{\"actor\":{\"reference\":\"Location/1\"},\"status\":\"accepted\"}],\"description\":\"grrrr\"", headers);
		
		Map<String, String> uriVariables = new HashMap<>();
		
//		uriVariables.put("start", "ge2018-07-04T00:00:00+01:00");
//		uriVariables.put("end", "le2018-07-18T23:59:59+01:00");
//		uriVariables.put("status", "free");
//		uriVariables.put("_format", "application/fhir+json");
//		uriVariables.put("_include", "Slot:schedule");
//		uriVariables.put("_include:recurse", "Schedule:actor:Practitioner");
//		uriVariables.put("_include:recurse", "Schedule:actor:Location");
//		uriVariables.put("searchFilter", "https://fhir.nhs.uk/STU3/ValueSet/GPConnect-OrganisationType-1|GP+Practice");
//		uriVariables.put("searchFilter", "https://fhir.nhs.uk/Id/ods-organization-code|GPC001");
		
		try {
			ResponseEntity<String> t = restTemplate.exchange("http://localhost:9091/fhir/Slot?start=ge2018-07-07T00:00:00-01:00&end=le2018-07-18T23:59:59-01:00&status=free&_include=Slot:schedule&searchFilter=https://fhir.nhs.uk/STU3/ValueSet/GPConnect-OrganisationType-1|GP Practice&searchFilter=https://fhir.nhs.uk/Id/ods-organization-code|GPC001", HttpMethod.GET, request, String.class);

			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		} catch (Exception e) {
			
		}
		

//		restTemplate.postForEntity("http://ec2-35-176-230-224.eu-west-2.compute.amazonaws.com/fihr/Appointment", "{ \"resourceType\": \"Appointment\", \"status\": \"booked\", \"appointmentType\": { \"coding\": [{ \"system\": \"http://example.org/appointment-type\", \"code\": \"394592004\", \"display\": \"General Medical Practitioner\" }], \"text\": \"In-person\" }, \"start\": \"2018-07-09T09:30:00.000Z\", \"end\": \"2018-07-09T09:40:00.000Z\", \"slot\": [{ \"reference\": \"Slot/8369\" }], \"participant\": [{ \"actor\": { \"reference\": \"Patient/3\" }, \"status\": \"accepted\" }, { \"actor\": { \"reference\": \"Practitioner/1\" }, \"status\": \"accepted\" }, { \"actor\": { \"reference\": \"Location/1\" }, \"status\": \"accepted\" }], \"description\": \"grrrr\" }", String.class);
//		ResponseEntity<String> t = restTemplate.exchange("http://localhost:9091/fhir/Slot?start=ge2018-07-07T00:00:00-01:00&end=le2018-07-18T23:59:59-01:00&status=free&_include=Slot:schedule&searchFilter=https://fhir.nhs.uk/STU3/ValueSet/GPConnect-OrganisationType-1|GP Practice&searchFilter=https://fhir.nhs.uk/Id/ods-organization-code|GPC001", HttpMethod.GET, request, String.class);
//		ResponseEntity<String> temp = restTemplate.getForEntity("http://localhost:9091/fhir/Slot?start=ge2018-07-04T00:00:00%2B01:00&end=le2018-07-18T23:59:59%2B01:00&status=free&_include=Slot:schedule&_include:recurse=Schedule:actor:Practitioner&_include:recurse=Schedule:actor:Location&searchFilter=https://fhir.nhs.uk/STU3/ValueSet/GPConnect-OrganisationType-1%7CGP%20Practice&searchFilter=https://fhir.nhs.uk/Id/ods-organization-code%7CGPC001", String.class);
//		String response = restTemplate.getForObject("http://localhost:9091/fhir/Slot?start=ge2018-07-07T00:00:00-01:00&end=le2018-07-18T23:59:59-01:00&status=free&_include=Slot:schedule&_include:recurse=Schedule:actor:Practitioner&_include:recurse=Schedule:actor:Location&searchFilter=https://fhir.nhs.uk/STU3/ValueSet/GPConnect-OrganisationType-1|GP Practice&searchFilter=https://fhir.nhs.uk/Id/ods-organization-code|GPC001", String.class);
	
		return null;
	}
	
//	private Bundle getBundle(String nhsNumber) {
//		
//		ResponseEntity<String> response = restHelper.getTemplate().exchange(gpRecordUrl, HttpMethod.POST, request, String.class);
//		
//		if(response.getStatusCode().is2xxSuccessful()) {
//			IBaseResource resource = FhirContext.forDstu3().newJsonParser().parseResource(response.getBody());			
//			Bundle bundle = null;
//			if (resource instanceof Bundle) {
//				bundle = (Bundle) resource;
//			}
//			return bundle;
//		}
//		return null;
//		
//	}
}
