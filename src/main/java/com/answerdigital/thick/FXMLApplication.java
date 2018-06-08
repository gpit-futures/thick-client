package com.answerdigital.thick;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.answerdigital.thick.view.MainView;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

@SpringBootApplication
public class FXMLApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
        launch(FXMLApplication.class, MainView.class, args);
	}
}