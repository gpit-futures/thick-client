package com.answerdigital.thick.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import com.answerdigital.thick.dto.Patient;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@FXMLController
public class PatientDialogController implements Initializable {

	@FXML
	private TableView<Patient> tableView;
	
    @FXML
    private TableColumn<Patient, String> name;
    
    @FXML
    private TableColumn<Patient, String> born;
    
    @FXML
    private TableColumn<Patient, String> address;
    
    @FXML
    private TableColumn<Patient, String> nhsNumber;
    
    @FXML
    private TableColumn<Patient, String> organisation;
    
    @FXML
	private Button cancelButton;
    
    @FXML
	private Button openButton;
    
    @Autowired
    private MainController mainController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setCellValueFactory(new PropertyValueFactory<Patient, String>("formattedName"));
    	born.setCellValueFactory(new PropertyValueFactory<Patient, String>("birthDate"));
    	address.setCellValueFactory(new PropertyValueFactory<Patient, String>("lineFormattedAddress"));
    	nhsNumber.setCellValueFactory(new PropertyValueFactory<Patient, String>("nhsNumber"));
    	organisation.setCellValueFactory(new PropertyValueFactory<Patient, String>("organisation"));
    	
    	tableView.getItems().setAll(mainController.getPatientList());
    	
    	attachEvents();
	}
	
	private void attachEvents() {
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
			
    	    @Override 
    	    public void handle(MouseEvent event) {
    	        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
    	            changePatient();  
    	            closeDialog();
    	        }
    	    }
    	});
		
		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				closeDialog();
			}
			
		});
		
		openButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				changePatient();
	            closeDialog();
			}
			
		});
	}
	
	private void closeDialog() {
	    ((Stage) tableView.getScene().getWindow()).close();
	}
	
	private void changePatient() {
		Patient patient = tableView.getSelectionModel().getSelectedItem();
		
		if (!patient.getNhsNumber().equals(mainController.getPatient().getNhsNumber())) {
			mainController.setPatient(patient); 
		}
		
		mainController.sendMessage(patient);
		
	}
	
}
