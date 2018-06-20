package com.answerdigital.thick.dto;

public class Name {

	private String use;
	private String family;
	private String[] given;
	private String[] prefix;
	
	public Name() {
		
	}
	
	public Name(String use, String family, String[] given, String[] prefix) {
		this.use = use;
		this.family = family;
		this.given = given;
		this.prefix = prefix;
	}
	
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	
	public String[] getGiven() {
		return given;
	}
	public void setGiven(String[] given) {
		this.given = given;
	}
	
	public String[] getPrefix() {
		return prefix;
	}
	public void setPrefix(String[] prefix) {
		this.prefix = prefix;
	}
	
	
}
