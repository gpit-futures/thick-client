package com.answerdigital.thick.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.answerdigital.client.connector.ClientConnector;
import com.answerdigital.pcaap.dto.Patient;
import com.answerdigital.thick.controller.MainController;
import com.answerdigital.thick.event.PatientContextChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageService {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private MainController mainController;
	
	@Value("frame.socket")
	private String frameSocket;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private ClientConnector clientConnector;
	
	private AtomicBoolean connecting = new AtomicBoolean(false);
	
	public MessageService() throws URISyntaxException {
		
		clientConnector = new ClientConnector(new URI("ws://localhost:1040")) {
				@Override
				public void onMessage( String message ) {
					System.out.println( "test overide: " + message );
					
					if (message == null) {
						context.publishEvent(new PatientContextChangedEvent(null));
					} else if (message.startsWith("fred")) {
						try {
							Patient test = objectMapper.readValue(message, Patient.class);
							context.publishEvent(new PatientContextChangedEvent(test));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
		};
	}
	
	@Async
	public void connectAndLogin(String accessToken) {
		connect();
		login(accessToken);
	}
	
	@Async
	public void connectAndPublish() {
		connect();
		publish();
	}
	
	private void connect() {
		System.out.println(clientConnector.isOpen());
		
		if (connecting.compareAndSet(false,  true)) {
			boolean initialAttempt = true;
			while (!clientConnector.isOpen()) {
				try {
					boolean connected = initialAttempt ? 
							clientConnector.connectBlocking() : clientConnector.reconnectBlocking();
					initialAttempt = false;
					Thread.sleep(1000);
				} catch (WebsocketNotConnectedException | NotYetConnectedException | InterruptedException e) {
					e.printStackTrace();
				}
			}
			connecting.set(false);
		}
	}
	
	private void login(String accessToken) {
		if (!connecting.get()) {
			try {
				clientConnector.send("{\"event\":\"token-context:changed\",\"data\":" + "\"" + accessToken + "\"}");
				Thread.sleep( 10 );
			} catch (WebsocketNotConnectedException | NotYetConnectedException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void publish() {
		if (!connecting.get()) {
			try {
				if(mainController.getPatient() != null) {
					clientConnector.sendPatientContext(mainController.getPatient());
				} else {
					clientConnector.sendPatientEnded();
				}
				Thread.sleep( 10 );
			} catch (WebsocketNotConnectedException | NotYetConnectedException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
