package com.answerdigital.thick.dto;

public class CarePlan extends ResponseDTO {

	private String status;
	private ReferencedConcept subject;
	private ReferencedConcept context;
	private String intent;
	private String title;
	private Period period;
	private ReferencedConcept[] author;
	private ReferencedConcept[] addresses;
	private Activity[] activity;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ReferencedConcept getSubject() {
		return subject;
	}
	public void setSubject(ReferencedConcept subject) {
		this.subject = subject;
	}
	
	public ReferencedConcept getContext() {
		return context;
	}
	public void setContext(ReferencedConcept context) {
		this.context = context;
	}
	
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	
	public ReferencedConcept[] getAuthor() {
		return author;
	}
	public void setAuthor(ReferencedConcept[] author) {
		this.author = author;
	}
	
	public ReferencedConcept[] getAddresses() {
		return addresses;
	}
	public void setAddresses(ReferencedConcept[] addresses) {
		this.addresses = addresses;
	}
	
	public Activity[] getActivity() {
		return activity;
	}
	public void setActivity(Activity[] activity) {
		this.activity = activity;
	}
	
}
