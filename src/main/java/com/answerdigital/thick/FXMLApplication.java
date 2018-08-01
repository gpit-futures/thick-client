package com.answerdigital.thick;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.answerdigital.thick.view.MainView;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

@EnableAsync
@SpringBootApplication
public class FXMLApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {
        launch(FXMLApplication.class, MainView.class, args);
	}
}