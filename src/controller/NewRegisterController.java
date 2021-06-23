package controller;

import java.io.IOException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.entities.Author;
import model.enums.Nationality;
import model.services.AuthorService;
import view.util.Alerts;

public class NewRegisterController {

	@FXML
	private Button btNewRegister;

	@FXML
	private Button btSearch;

	@FXML
	private ImageView btEdit;

	@FXML
	private ImageView btDelete;

	@FXML
	private Button btExit;

	@FXML
	private TextField inputTitle;

	@FXML
	private TextField inputCompany;

	@FXML
	private ComboBox<?> selectBoxYer;

	@FXML
	private TextField inputISBN;

	@FXML
	private TextField inputCode;

	@FXML
	private TextField inputNameAuthor;

	@FXML
	private ComboBox<Nationality> selectBoxNationality;

	@FXML
	private TextArea inputTextAreaBiography;

	@FXML
	private Button btCancel;

	@FXML
	private Button btSave;

	@FXML
	private ImageView shapeBookImg;

	private AuthorService service = new AuthorService();
	
	@FXML
	void fillBoxNationality() {
		selectBoxNationality.getItems().setAll(Nationality.values());
	}
	

	@FXML
	void btSaveAction(ActionEvent e) throws IOException {
		Alerts.showOptionalAlert("Deseja continuar?", "Você está prestes a salvar APENAS o autor! Continuar mesmo assim?");
		System.out.println(instatianteAuthor());
	}

	private Author instatianteAuthor() {
		Author author = new Author();
		author.setId(null);
		author.setName(inputNameAuthor.getText());
		author.setNationality(selectBoxNationality.getValue());
		author.setBiography(inputTextAreaBiography.getText());
		return author;
	}

}
