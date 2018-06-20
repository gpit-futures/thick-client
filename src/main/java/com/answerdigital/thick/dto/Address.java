package com.answerdigital.thick.dto;

public class Address {

	private String use;
	private String type;
	private String[] line;
	private String city;
	private String district;
	private String postalCode;
	
	public Address() {
		
	}

	public Address(String use, String type, String[] line, String city, String district, String postalCode) {
		this.use = use;
		this.type = type;
		this.line = line;
		this.city = city;
		this.district = district;
		this.postalCode = postalCode;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getLine() {
		return line;
	}

	public void setLine(String[] line) {
		this.line = line;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
