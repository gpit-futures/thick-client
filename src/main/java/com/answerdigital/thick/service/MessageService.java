package com.answerdigital.thick.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.answerdigital.thick.dto.Patient;

@Service
public class MessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${patient.exchange.key}")
    private String patientExchangeKey;
	
	@Value("${patient.context.changed.key}")
    private String patientContextChangedKey;
	
	public void publish(Patient patient) {
		this.rabbitTemplate.convertAndSend(patientExchangeKey, patientContextChangedKey, patient);
	}
	
}
