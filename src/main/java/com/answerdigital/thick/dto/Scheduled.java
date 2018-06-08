package com.answerdigital.thick.dto;

public class Scheduled {
	private String scheduledTiming;
	private Period scheduledPeriod;
	private String scheduledString;

	public String getScheduledTiming() {
		return scheduledTiming;
	}

	public void setScheduledTiming(String scheduledTiming) {
		this.scheduledTiming = scheduledTiming;
	}

	public Period getScheduledPeriod() {
		return scheduledPeriod;
	}

	public void setScheduledPeriod(Period scheduledPeriod) {
		this.scheduledPeriod = scheduledPeriod;
	}

	public String getScheduledString() {
		return scheduledString;
	}

	public void setScheduledString(String scheduledString) {
		this.scheduledString = scheduledString;
	}

}
