package view.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Alerts {
	

	//return true if button yes pressed
	public static boolean showOptionalAlert(String title, String message) {
		ButtonType btYes = new ButtonType("Sim", ButtonData.YES);
		ButtonType btNo = new ButtonType("Não", ButtonData.NO);
		
		Alert alert = new Alert(AlertType.CONFIRMATION, null, btYes, btNo);
		alert.setHeaderText(message);
		alert.setTitle(title);
		
		Optional<ButtonType> option = alert.showAndWait(); 
		
		if(option.get() == btYes) {
			return true;
		}else {
			return false;
		}
	}
}
