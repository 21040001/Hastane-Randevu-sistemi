package com.Hastane.RezerveSystem.View;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
	@FXML
	public Label myMessage;
	public void generated(ActionEvent event) {
		Random rand = new Random();
		int myrand = rand.nextInt(50)+1;
		myMessage.setText(Integer.toString(myrand));
		
	}

	
}
