package com.answerdigital.thick.dto;

public class CodeableConcept {

	private Coding[] coding;
	private String text;

	public Coding[] getCoding() {
		return coding;
	}

	public void setCoding(Coding[] coding) {
		this.coding = coding;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
