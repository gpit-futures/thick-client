package com.answerdigital.thick.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;

import com.answerdigital.thick.FXMLApplication;
import com.answerdigital.thick.dto.Patient;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			patientList = FXCollections.observableArrayList(patientRestService.readAll());
		} catch (RestClientException e) {
			serverDown = true;
		}
		
		serverStatus.setText(serverDown ? "OFFLINE" : "ONLINE");
		serverUrl.setText(baseUrl);
		setPatient(patientList.get(0));
		
		attachEvents();
		update();
		sendMessage(patient.get());
	}

	public ObservableList<Patient> getPatientList() {
		return patientList;
	}
	
	public final Patient getPatient() {
		return patient.get();
	}
	  
	public final void setPatient(Patient val) {
	    patient.set(val);
	    update();
	}
	
	private void update() {
		titleField.setText(patient.get().getPrimaryName().getPrefix()[0]);
		forenameField.setText(patient.get().getPrimaryName().getGiven()[0]);
		surnameField.setText(patient.get().getPrimaryName().getFamily());
		sexField.setText(patient.get().getGender());
		dobField.setText(patient.get().getBirthDate());
		nhsNumberField.setText(patient.get().getNhsNumber());
		homePhoneField.setText(patient.get().getTelecom()[0].getValue());
		addressArea.setText(patient.get().getFormattedAddress());
		
		bannerBirthDate.setText(patient.get().getBirthDate());
		bannerGender.setText(patient.get().getGender());
		bannerName.setText(patient.get().getFormattedName());
		bannerNhsNumber.setText(patient.get().getNhsNumber());
		bannerOrganisation.setText(patient.get().getOrganisation());
	}
	
	protected void sendMessage(Patient patient) {
		try {
			messageService.publish(patient);
		} catch (AmqpException e) {
		}
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
