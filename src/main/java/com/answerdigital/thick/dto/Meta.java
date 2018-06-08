package com.answerdigital.thick.dto;

public class Meta {

	private Long versionId;
	private String lastUpdated;
	private String[] profile;
	
	public Long getVersionId() {
		return versionId;
	}
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String[] getProfile() {
		return profile;
	}
	public void setProfile(String[] profile) {
		this.profile = profile;
	}
}
