package controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class SearchController {

	@FXML
	protected void changeSceneToNewRegist() throws IOException {
		System.out.println("Funfou!");
    	Main.changeScene(FXMLLoader.load(getClass().getResource("/view/fxml/newRegisterView.fxml")));
	}
}
