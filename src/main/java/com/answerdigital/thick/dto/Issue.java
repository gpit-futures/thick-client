package com.answerdigital.thick.dto;

public class Issue {

	private String severity;
	private String code;
	private String diagnostics;
	
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDiagnostics() {
		return diagnostics;
	}
	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}
	
}
