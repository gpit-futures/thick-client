package com.answerdigital.thick.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.answerdigital.client.connector.ClientConnector;
import com.answerdigital.pcaap.dto.Patient;
import com.answerdigital.thick.event.PatientContextChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageService {
	
	@Autowired
	private ApplicationContext context;
	
	@Value("frame.socket")
	private String frameSocket;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private ClientConnector clientConnector;

	
	public MessageService() {
		try {
			clientConnector = new ClientConnector(new URI("ws://localhost:1040")) {
				@Override
				public void onMessage( String message ) {
					System.out.println( "test overide: " + message );
					
					if (message == null) {
						context.publishEvent(new PatientContextChangedEvent(null));
					} else {
						Patient test = null;
						try {
							test = objectMapper.readValue(message, Patient.class);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						context.publishEvent(new PatientContextChangedEvent(test));
					}
				}
			};
			clientConnector.connectBlocking();
			clientConnector.sendPatientEnded();
			
		} catch (URISyntaxException | NotYetConnectedException | InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void publish(Patient patient) {
		
		try {
			if(patient != null) {
				clientConnector.sendPatientContext(patient);
			} else {
				clientConnector.sendPatientEnded();
			}
			Thread.sleep( 10 );
		} catch (InterruptedException | NotYetConnectedException e) {
			e.printStackTrace();
		}
	}
	
}
