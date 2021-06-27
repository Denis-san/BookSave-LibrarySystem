package controller;

import java.io.File;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.entities.Author;
import model.entities.Book;
import model.enums.Nationality;
import model.services.AuthorService;
import model.services.BookService;
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
	private TextField inputYear;

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

	private AuthorService authorService = new AuthorService();
	private BookService bookService = new BookService();

	@FXML
	void imgSelectAction() {
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Images files (*.png)", "*.png");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(extFilter);
		File img = fileChooser.showOpenDialog(shapeBookImg.getScene().getWindow());

		if (img != null) {
			shapeBookImg.setImage(new Image(img.toURI().toString()));
		}

	}

	@FXML
	void onKeyActionInputName() {

		if (inputNameAuthor.getText() != null) {

			if (inputNameAuthor.getText().length() >= 8) {

				Author author = loadAuthor(inputNameAuthor.getText());

				inputTextAreaBiography.setText((author != null) ? author.getBiography() : null);
				selectBoxNationality.setValue((author != null) ? author.getNationality() : null);
			}
		}
	}

	@FXML
	void fillBoxNationality() {
		selectBoxNationality.getItems().setAll(Nationality.values());
	}

	@FXML
	void btCancelAction() {
		TextField[] textFields = { inputTitle, inputCompany, inputYear, inputCode, inputISBN, inputNameAuthor };
		for (TextField field : textFields) {
			field.setText(null);
		}

		inputTextAreaBiography.setText(null);
		selectBoxNationality.setValue(null);
	}

	@FXML
	void btSaveAction(ActionEvent e) {
		Author author = null;
		Book book = null;

		if (fieldsOfBookIsNull()) {
			if (!fieldsOfAuthorIsNull()) {
				if (Alerts.showOptionAuthorSave()) {
					authorService.saveAuthor(instantiateAuthor());
					Alerts.showSucessDataSaveAlert();
					return;
				}
			}
			Alerts.showFieldsInvalidAlert();
		} else {

			if (fieldsOfAuthorIsNull()) {
				Alerts.showFieldsInvalidAlert();
				return;
			}

			if(!yearInputIsValid()) {
				Alerts.showYearInputsInvalid();
				return;
			}
			
			author = instantiateAuthor();
			book = instantiateBook(author);

			if (bookAlreadyExists(book.getTitle())) {
				Alerts.showBookExistsAlert();
				return;
			}

			if (loadAuthor(inputNameAuthor.getText()) != null) {
				bookService.saveBook(instantiateBook(loadAuthor(inputNameAuthor.getText())));
				Alerts.showSucessDataSaveAlert();
				return;
			}

			authorService.saveAuthor(author);
			bookService.saveBook(book);

			Alerts.showSucessDataSaveAlert();
		}

	}

	private Book instantiateBook(Author author) {
		Book book = null;
		book = new Book();
		book.setId(null);
		book.setAuthor(author);
		book.setTitle(inputTitle.getText());
		book.setPublishCompany(inputCompany.getText());
		book.setCode(inputCode.getText());
		book.setYear(Integer.parseInt(inputYear.getText()));
		book.setCloak(shapeBookImg.getImage().getUrl());
		return book;
	}

	private Author instantiateAuthor() {
		Author author = new Author();
		author.setId(null);
		author.setName(inputNameAuthor.getText());
		author.setNationality(selectBoxNationality.getValue());
		author.setBiography(inputTextAreaBiography.getText());
		return author;
	}

	private boolean fieldsOfBookIsNull() {
		TextField[] textFields = { inputTitle, inputCompany, inputYear, inputCode, inputISBN };
		for (TextField field : textFields) {
			if (field.getText() == null || field.getText().equals("")) {
				return true;
			}
		}

		if (shapeBookImg.getImage() == null || shapeBookImg.getImage().getUrl() == null) {
			return true;
		}
		return false;
	}

	private boolean fieldsOfAuthorIsNull() {
		if (inputNameAuthor.getText() == null || inputNameAuthor.getText().equals("")) {
			return true;
		}

		if (selectBoxNationality.getValue() == null) {
			return true;
		}

		if (inputTextAreaBiography.getText() == null || inputTextAreaBiography.getText().equals("")) {
			return true;
		}

		return false;
	}

	private Author loadAuthor(String name) {
		Author author = null;
		try {
			author = authorService.findAuhtor(name);
		} catch (SQLException e) {
			return null;
		}
		return author;
	}

	private boolean bookAlreadyExists(String title) {
		try {
			if (bookService.findByTitle(title).isEmpty()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	private boolean yearInputIsValid() {
		String format = inputYear.getText();
		int year = 0;

		if (format.length() == 4) {
			for (int i = 0; i < format.length(); i++) {
				if (!Character.isDigit(format.charAt(i))) {
					return false;
				}
			}
			
			year = Integer.parseInt(format);
			
			//change to get actual year! 
			if(year <= 1200 || year >= 2022) {
				return false;
			}
			
			return true;
		} else {
			return false;
		}

	}

}
