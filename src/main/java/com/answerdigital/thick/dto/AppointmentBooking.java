package com.answerdigital.thick.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "resourceType", "status", "appointmentType", "start", "end", "slot", "participant",
		"description" })
public class AppointmentBooking {

	@JsonProperty("resourceType")
	private String resourceType;
	@JsonProperty("status")
	private String status;
	@JsonProperty("appointmentType")
	private CodeableConcept appointmentType;
	@JsonProperty("start")
	private String start;
	@JsonProperty("end")
	private String end;
	@JsonProperty("slot")
	private List<Slot> slot = null;
	@JsonProperty("description")
	private String description;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("resourceType")
	public String getResourceType() {
		return resourceType;
	}

	@JsonProperty("resourceType")
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("start")
	public String getStart() {
		return start;
	}

	@JsonProperty("start")
	public void setStart(String start) {
		this.start = start;
	}

	@JsonProperty("end")
	public String getEnd() {
		return end;
	}

	@JsonProperty("end")
	public void setEnd(String end) {
		this.end = end;
	}

	@JsonProperty("slot")
	public List<Slot> getSlot() {
		return slot;
	}

	@JsonProperty("slot")
	public void setSlot(List<Slot> slot) {
		this.slot = slot;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}