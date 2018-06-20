package com.answerdigital.thick.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.answerdigital.thick.dto.Patient;
import com.answerdigital.thick.event.PatientContextChangedEvent;

@Service
public class MessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ApplicationContext context;
	
	@Value("${patient.exchange.key}")
    private String patientExchangeKey;
	
	@Value("${patient.context.changed.key}")
    private String patientContextChangedKey;
	
	@Value("${patient.context.ended.key}")
    private String patientContextEndedKey;
	
	public void publish(Patient patient) {
		if(patient != null) {
			rabbitTemplate.convertAndSend(patientExchangeKey, patientContextChangedKey, patient);
		} else {
			rabbitTemplate.convertAndSend(patientExchangeKey, patientContextEndedKey, "patient-context:ended");
		}
	}
	
	@RabbitListener(queues = {"patient-context-changed-queue"})
	public void recievePatientContext(Patient patient) {
		context.publishEvent(new PatientContextChangedEvent(patient));
	}
	
	@RabbitListener(queues = {"patient-context-ended-queue"})
	public void recievePatientContextEnded(byte[] message) {
		context.publishEvent(new PatientContextChangedEvent(null));
	}
	
}
