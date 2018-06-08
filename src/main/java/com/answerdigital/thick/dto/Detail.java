package com.answerdigital.thick.dto;

public class Detail {
	
	private String scheduledString;
	private String status;
	private Product productCodeableConcept;
	
	public String getScheduled() {
		return scheduledString;
	}
	public void setScheduled(String scheduledString) {
		this.scheduledString = scheduledString;
	}
	
	public Product getProductCodeableConcept() {
		return productCodeableConcept;
	}
	public void setProductCodeableConcept(Product productCodeableConcept) {
		this.productCodeableConcept = productCodeableConcept;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
