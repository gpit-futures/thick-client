package com.answerdigital.thick.dto;

public class ResponseDTO {

	private String resourceType;
	private Text text;
	private Issue[] issue;
	private String id;
	private Meta meta;
	private Identifier[] identifier;
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	
	public Issue[] getIssue() {
		return issue;
	}
	public void setIssue(Issue[] issue) {
		this.issue = issue;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	public Identifier[] getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Identifier[] identifier) {
		this.identifier = identifier;
	}

}
