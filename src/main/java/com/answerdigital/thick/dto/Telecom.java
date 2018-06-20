package com.answerdigital.thick.dto;

public class Telecom {

	private String system;
	private String value;
	private String use;
	
	public Telecom() {
		
	}

	public Telecom(String system, String value, String use) {
		this.system = system;
		this.value = value;
		this.use = use;
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

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

}
