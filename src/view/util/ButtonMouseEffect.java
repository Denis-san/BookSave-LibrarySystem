package view.util;

import javafx.scene.control.Button;

public class ButtonMouseEffect {
	public static void changeBorderButton(Button bt, boolean active) {
		if (active) {
			bt.setStyle("-fx-background-radius: 50; -fx-background-color: #f2f2f2;");
		} else {
			bt.setStyle("-fx-background-radius: none; -fx-background-color: white;");
		}
	}

	
}
