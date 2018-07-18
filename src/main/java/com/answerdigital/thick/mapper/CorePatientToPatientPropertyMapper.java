package com.answerdigital.thick.mapper;

import org.springframework.stereotype.Component;

import com.answerdigital.thick.dto.Address;
import com.answerdigital.thick.dto.CorePatient;
import com.answerdigital.thick.dto.Identifier;
import com.answerdigital.thick.dto.Name;
import com.answerdigital.thick.dto.Patient;
import com.answerdigital.thick.dto.Telecom;

@Component
public class CorePatientToPatientPropertyMapper {
	
	public Patient map(CorePatient corePatient) {
		
		Patient patient = new Patient();
		
		patient.setResourceType("Patient");
		patient.setId(corePatient.getId());
		patient.setIdentifier(new Identifier[] {
				new Identifier("https://fhir.nhs.uk/Id/nhs-number", corePatient.getNhsNumber())});
		
		patient.setActive(true);
		patient.setName(new Name[] {
				new Name("usual", corePatient.getLastName(), new String[] {
						corePatient.getFirstName()}, new String[] {corePatient.getTitle()})});
		
		patient.setTelecom(new Telecom[] {
				new Telecom("phone", corePatient.getPhone(), "home")});
		
		patient.setGp(corePatient.getGp());
		patient.setGender(corePatient.getGender());
		patient.setBirthDate(corePatient.getDateOfBirth());
		patient.setAddress(new Address[] {
				new Address("work", "both", new String[] {
						corePatient.getAddress().getLine1(), 
						corePatient.getAddress().getLine2()}, 
						corePatient.getAddress().getLine3(), 
						corePatient.getAddress().getLine4(), 
						corePatient.getAddress().getPostcode())});
		
		return patient;
	}

}