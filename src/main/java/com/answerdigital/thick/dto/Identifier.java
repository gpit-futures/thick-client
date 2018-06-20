package com.answerdigital.thick.dto;

public class Identifier {

	private String system;
	private String value;
	
	public Identifier() {
		
	}

	public Identifier(String system, String value) {
		this.system = system;
		this.value = value;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
