package com.answerdigital.thick.dto;

public class ReferencedConcept {
	
	private String reference;
	private String display;
	private Identifier identifier;

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

}
