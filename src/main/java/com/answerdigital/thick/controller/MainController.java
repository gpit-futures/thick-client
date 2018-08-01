package com.answerdigital.thick.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestClientException;

import com.answerdigital.thick.FXMLApplication;
import com.answerdigital.pcaap.dto.Patient;
import com.answerdigital.thick.event.PatientContextChangedEvent;
import com.answerdigital.thick.service.MessageService;
import com.answerdigital.thick.service.PatientRestService;
import com.answerdigital.thick.view.PatientDialogView;

import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.application.Platform;

@FXMLController
public class MainController implements Initializable {

	@FXML
	private Label serverStatus;
	
	@FXML
	private Label serverUrl;
	
	@FXML
	private Label bannerName;
	
	@FXML
	private Label bannerBirthDate;
	
	@FXML
	private Label bannerGender;
	
	@FXML
	private Label bannerNhsNumber;
	
	@FXML
	private Label bannerOrganisation;
	
	@FXML
	private ToggleGroup buttonGroup;
	
	@FXML
	private TextField titleField;
	
	@FXML
	private TextField forenameField;
	
	@FXML
	private TextField surnameField;
	
	@FXML
	private TextField sexField;
	
	@FXML
	private TextField dobField;
	
	@FXML
	private TextField nhsNumberField;
	
	@FXML
	private TextField homePhoneField;
	
	@FXML
	private TextField workPhoneField;
	
	@FXML
	private TextField mobilePhoneField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField patientGuidField;
	
	@FXML
	private TextArea addressArea;
	
	@FXML
	private FlowPane patientBanner;
	
	@FXML
	private FlowPane nullPatientBanner;
	
	private ObjectProperty<Patient> patient = new SimpleObjectProperty<>();
	
	@Autowired
	private PatientRestService patientRestService;
	
	@Autowired
	private MessageService messageService;
	
	@Value("${base.url}")
	private String baseUrl;
	
	private ObservableList<Patient> patientList;
	
	private boolean serverDown;
	 
	public void showPatientDialog(Event event) throws IOException {
        FXMLApplication.showView(PatientDialogView.class, javafx.stage.Modality.APPLICATION_MODAL);
	}
	
	public void clearPatientContext(Event event) throws IOException {
		setPatient(null);
		sendMessage(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			patientList = FXCollections.observableArrayList(patientRestService.readAll());
		} catch (RestClientException e) {
			serverDown = true;
		}
		
		serverStatus.setText(serverDown ? "OFFLINE" : "ONLINE");
		serverUrl.setText(baseUrl);
		
		patientBanner.setVisible(false);
		
		attachEvents();
	}

	public ObservableList<Patient> getPatientList() {
		return patientList;
	}
	
	public final Patient getPatient() {
		return patient.get();
	}
	  
	@EventListener
	public void recivePatient(PatientContextChangedEvent event) {
		setPatient(event.getPatient());
	}
	
	public final void setPatient(Patient val) {
	    patient.set(val);
	    update();
	}
	
	private void update() {
		boolean hasPatient = patient.get() != null;
		if (hasPatient) {
			updatePatient(patient.get());
		} else {
			clearPatient();
		}
		patientBanner.setVisible(hasPatient);
		nullPatientBanner.setVisible(!hasPatient);
	}
	
	private void updatePatient(Patient patient) {
		titleField.setText(patient.getPrimaryName().getPrefix()[0]);
		forenameField.setText(patient.getPrimaryName().getGiven()[0]);
		surnameField.setText(patient.getPrimaryName().getFamily());
		sexField.setText(patient.getGender());
		dobField.setText(patient.getBirthDate());
		nhsNumberField.setText(patient.getNhsNumber());
		homePhoneField.setText(patient.getTelecom()[0].getValue());
		addressArea.setText(patient.getFormattedAddress());
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	bannerBirthDate.setText(patient.getBirthDate());
        		bannerGender.setText(patient.getGender());
        		bannerName.setText(patient.getFormattedName());
        		bannerNhsNumber.setText(patient.getNhsNumber());
        		bannerOrganisation.setText(patient.getOrganisation());
            }
        });
	}
	
	private void clearPatient() {
		titleField.setText("");
		forenameField.setText("");
		surnameField.setText("");
		sexField.setText("");
		dobField.setText("");
		nhsNumberField.setText("");
		homePhoneField.setText("");
		addressArea.setText("");
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	bannerBirthDate.setText("");
        		bannerGender.setText("");
        		bannerName.setText("");
        		bannerNhsNumber.setText("");
        		bannerOrganisation.setText("");
            }
        });
	}
	
	protected void sendMessage(Patient patient) {
			messageService.connectAndPublish();
	}
	
	private void attachEvents() {
		buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
	            if (buttonGroup.getSelectedToggle() != null) {
	              System.out.println(buttonGroup.getSelectedToggle().getUserData().toString());
	            }
			}
        });
	}
}
