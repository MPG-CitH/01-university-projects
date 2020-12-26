package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.gui.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

public class AlertTemplate {
	Alert alert;
	
	
	public AlertTemplate(AlertType type, String message) {
		this.alert = new Alert(type, message);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
	}
	
	public boolean show() {
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent () && result.get () == ButtonType.OK ) {
			return true;
		} else {
			return false;
		}
	}
	

}
