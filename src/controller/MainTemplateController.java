package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.database.exception.DbException;
import view.PathViews;
import view.util.Alerts;

public class MainTemplateController {

	@FXML
	private BorderPane mainTemplatePane;

	@FXML
	private Button btNewRegister;

	@FXML
	private Button btSearch;

	@FXML
	private Button btEdit;

	@FXML
	private Button btDelete;

	@FXML
	private Button btExit;

	@FXML
	private Label labelTitle;

	@FXML
	void btDeleteAction(ActionEvent event) {
		try {
			changeScreen("Deletar", PathViews.PATH_DELETE_VIEW);
			changeBackColorButton(btDelete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btEditAction(ActionEvent event) {
		try {
			changeScreen("Editar", PathViews.PATH_EDIT_VIEW);
			changeBackColorButton(btEdit);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	void btNewRegisterAction(ActionEvent event) {

		try {
			changeScreen("Cadastro", PathViews.PATH_NEW_REGISTER_VIEW);
			changeBackColorButton(btNewRegister);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DbException error) {
			System.out.println("Caiu aqui!");
		}

	}
	
	@FXML
	void btSearchAction(ActionEvent event) {
		try {
			changeScreen("Buscar", PathViews.PATH_SEARCH_VIEW);
			changeBackColorButton(btSearch);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void exitProgram(ActionEvent event) {
		if(Alerts.showOptionExit()) {
			Stage stage = (Stage) btExit.getScene().getWindow();
			stage.close();
		}
	}

	private void changeScreen(String title, String pathOfView) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource(pathOfView));
		if (mainTemplatePane.getCenter() == null || !mainTemplatePane.getCenter().getParent().equals(root)) {
			mainTemplatePane.setCenter(root);
			labelTitle.setText(title);
		}

	}

	private void changeBackColorButton(Button button) {
		Button[] allButtonsBar = { btNewRegister, btSearch, btEdit, btDelete };
		button.setStyle("-fx-background-color: white; -fx-background-radius: none");
		for (Button bt : allButtonsBar) {
			if (!bt.equals(button)) {
				bt.setStyle("-fx-background-color: none");
			}

		}
	}
}
