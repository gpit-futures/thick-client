package com.answerdigital.thick.dto;

import java.text.MessageFormat;
import java.util.Objects;

import org.springframework.util.StringUtils;

public class Patient extends ResponseDTO {
	
	private Boolean active;
	private Name[] name;
	private Telecom[] telecom;
	private String gender;
	private String birthDate;
	private Address[] address;
	private MaritalStatus maritalStatus;
	
	public Boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
    public Name[] getName() {
    	return name;
	}
	public void setName(Name[] name) {
		this.name = name;
	}
	
	public Telecom[] getTelecom() {
		return telecom;
	}
	public void setTelecom(Telecom[] telecom) {
		this.telecom = telecom;
	}
	
	public String getBirthDate() {
		return birthDate.indexOf("T") != -1 ? birthDate.substring(0, birthDate.indexOf("T")) : birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public Address[] getAddress() {
		return address;
	}
	public void setAddress(Address[] address) {
		this.address = address;
	}
	
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getFormattedName() {
		return MessageFormat.format("{0}, {1} ({2})", 
				name[0].getFamily().toUpperCase(), 
				StringUtils.capitalize(name[0].getGiven()[0]), StringUtils.capitalize(name[0].getPrefix()[0]));
	}
	
	public String getFormattedAddress() {
		return getFormattedAddress(false);
	}
	
	public String getLineFormattedAddress() {
		return getFormattedAddress(true);
	}
	
	private String getFormattedAddress(boolean singleLine) {
		return String.join("," + (singleLine ? " " : "\n"), address[0].getLine()[0], Objects.toString(address[0].getCity(), ""), 
				Objects.toString(address[0].getDistrict(), ""), Objects.toString(address[0].getPostalCode(), ""));
	}
	
	public String getNhsNumber() {
		return getIdentifier()[0].getValue();
	}
	
	public String getOrganisation() {
		return "Alpha Surgery";
	}
	
	public Name getPrimaryName() {
		return name[0];
	}

}
