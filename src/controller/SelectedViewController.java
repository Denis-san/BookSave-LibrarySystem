package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.entities.Book;

public class SelectedViewController implements Initializable {

	@FXML
	private ImageView cloakBook;

	@FXML
	private TextField inputTitle;

	@FXML
	private TextField inputCompany;

	@FXML
	private TextField inputYear;

	@FXML
	private TextField inputCode;

	@FXML
	private TextField inputNameAuthor;

	@FXML
	private TextField inputNationality;

	@FXML
	private TextArea inputBiography;

	@FXML
	private BorderPane pane;

	@FXML
	void teste() {
		System.out.println(pane.getScene().getUserData());
	}

	public void teste2() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		new Thread(() -> {
			while (pane.isVisible()) {
				Book book = (Book) pane.getScene().getUserData();
				if (book != null) {
					inputTitle.setText(book.getTitle());
					inputCompany.setText(book.getPublishCompany());
					inputYear.setText(book.getYear().toString());
					inputCode.setText(book.getCode());
					inputNameAuthor.setText(book.getAuthor().getName());
					inputNationality.setText(String.valueOf(book.getAuthor().getNationality()));
					inputBiography.setText(book.getAuthor().getBiography());
					cloakBook.setImage(new Image(book.getCloak()));

					Thread.currentThread().stop();
					break;
				}
			}
		}).start();
	}

}
