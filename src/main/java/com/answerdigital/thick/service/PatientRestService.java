package com.answerdigital.thick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.answerdigital.pcaap.dto.Patient;

@Service
public class PatientRestService extends RestService<Patient> {

	@Value("${patient.namespace}")
	private String patientNamespace;

	@Autowired
	public PatientRestService() {
		super(Patient.class);
	}

	@Override
	protected String namespace() {
		return patientNamespace;
	}

}
