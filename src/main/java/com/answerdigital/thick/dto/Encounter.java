package com.answerdigital.thick.dto;

public class Encounter extends ResponseDTO {

	private String status;
	private ReferencedConcept subject;
	private ReferencedConcept[] particpant;
	private Period period;
	private CodeableConcept[] reason;
	private ReferencedConcept[] diagnosis;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReferencedConcept getSubject() {
		return subject;
	}

	public void setSubject(ReferencedConcept subject) {
		this.subject = subject;
	}

	public ReferencedConcept[] getParticpant() {
		return particpant;
	}

	public void setParticpant(ReferencedConcept[] particpant) {
		this.particpant = particpant;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public CodeableConcept[] getReason() {
		return reason;
	}

	public void setReason(CodeableConcept[] reason) {
		this.reason = reason;
	}

	public ReferencedConcept[] getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(ReferencedConcept[] diagnosis) {
		this.diagnosis = diagnosis;
	}

}
