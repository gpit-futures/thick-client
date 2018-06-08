package com.answerdigital.thick.dto;

public class Observation extends ResponseDTO {

	private CodeableConcept code;
	private ReferencedConcept subject;
	private ReferencedConcept context;
	private String status;
	private Category[] category;
	private String effectiveDateTime;
	private Performer[] performer;
	private ValueQuantity valueQuantity;
	private Interpretation interpretation;
	private ReferenceRange[] referenceRange;

	public CodeableConcept getCode() {
		return code;
	}
	public void setCode(CodeableConcept code) {
		this.code = code;
	}

	public ReferencedConcept getSubject() {
		return subject;
	}
	public void setSubject(ReferencedConcept subject) {
		this.subject = subject;
	}

	public ReferencedConcept getContext() {
		return context;
	}
	public void setContext(ReferencedConcept context) {
		this.context = context;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Category[] getCategory() {
		return category;
	}
	public void setCategory(Category[] category) {
		this.category = category;
	}
	
	public String getEffectiveDateTime() {
		return effectiveDateTime;
	}
	public void setEffectiveDateTime(String effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}
	
	public Performer[] getPerformer() {
		return performer;
	}
	public void setPerformer(Performer[] performer) {
		this.performer = performer;
	}
	
	public ValueQuantity getValueQuantity() {
		return valueQuantity;
	}
	public void setValueQuantity(ValueQuantity valueQuantity) {
		this.valueQuantity = valueQuantity;
	}
	
	public Interpretation getInterpretation() {
		return interpretation;
	}
	public void setInterpretation(Interpretation interpretation) {
		this.interpretation = interpretation;
	}
	
	public ReferenceRange[] getReferenceRange() {
		return referenceRange;
	}
	public void setReferenceRange(ReferenceRange[] referenceRange) {
		this.referenceRange = referenceRange;
	}
}
