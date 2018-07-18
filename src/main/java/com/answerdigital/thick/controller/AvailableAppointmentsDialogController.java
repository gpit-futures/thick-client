package com.answerdigital.thick.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.answerdigital.thick.service.AppointmentRestService;

import de.felixroske.jfxsupport.FXMLController;

@FXMLController
public class AvailableAppointmentsDialogController implements Initializable {
	
	@Autowired
	@SuppressWarnings("unused")
    private MainController mainController;
	
	@Autowired
	private AppointmentRestService appointmentRestService;
	
	@FXML
	private RadioButton ods1;
	
	@FXML
	private RadioButton ods2;
	
	@Value("${appointment.url.1}")
	private String url1;
	
	@Value("${appointment.url.2}")
	private String url2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	attachEvents();
	}
	
	private void attachEvents() {
		attachEvent(ods1, url1);
		attachEvent(ods2, url2);
	}
	
	private void attachEvent(RadioButton control, String url) {
		control.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				appointmentRestService.bookAppointment(url);
				((Stage) ods1.getScene().getWindow()).close();
			}
			
		});
	}
	
}
