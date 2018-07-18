package com.answerdigital.thick.event;

import com.answerdigital.thick.dto.Patient;

public class PatientContextChangedEvent {
	
	private Patient patient;

	public PatientContextChangedEvent(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
