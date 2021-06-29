package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.entities.Author;
import model.entities.Book;
import model.enums.Nationality;
import model.services.AuthorService;
import model.services.BookService;
import view.util.Alerts;
import view.util.ButtonMouseEffect;

public class EditController implements Initializable {

	@FXML
	private BorderPane pane;

	@FXML
	private Button btSearch;

	@FXML
	private Button btCancel;

	@FXML
	private Button btSave;

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
	private ComboBox<Nationality> selectNationality;

	@FXML
	private TextArea inputBiography;

	private Book book = null;

	private AuthorService authorService = new AuthorService();
	private BookService bookService = new BookService();

	@FXML
	void fillBoxNationality() {
		selectNationality.getItems().setAll(Nationality.values());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btCancel.setOnMouseEntered(event -> {
			ButtonMouseEffect.changeBorderButton(btCancel, true);
		});

		btCancel.setOnMouseExited(event -> {
			ButtonMouseEffect.changeBorderButton(btCancel, false);
		});

		btSave.setOnMouseEntered(event -> {
			ButtonMouseEffect.changeBorderButton(btSave, true);
		});

		btSave.setOnMouseExited(event -> {
			ButtonMouseEffect.changeBorderButton(btSave, false);
		});

		btSearch.setOnMouseEntered(event -> {
			ButtonMouseEffect.changeBorderButton(btSearch, true);
		});

		btSearch.setOnMouseExited(event -> {
			ButtonMouseEffect.changeBorderButton(btSearch, false);
		});

	}

	@FXML
	void btCancelAction() {
		TextField[] textFields = { inputTitle, inputCompany, inputYear, inputCode, inputNameAuthor };
		for (TextField field : textFields) {
			field.setText(null);
		}

		inputBiography.setText(null);
		selectNationality.setValue(null);
	}

	@FXML
	void btSaveAction() {

		if (fieldsOfBookIsNull()) {
			if (!fieldsOfAuthorIsNull()) {
				if (Alerts.showOptionAuthorSave()) {
					if (loadAuthor(instantiateAuthor().getName()) != null) {
						try {
							if (Alerts.showOptionAuthorSave()) {
								authorService.updateAuthor(instantiateAuthor());
								Alerts.showSucessDataSaveAlert();
								return;
							}
						} catch (SQLException e) {
							Alerts.showErrorAlert(e);
						}
						
						return;
					}

					return;
				}
			}
			Alerts.showFieldsInvalidAlert();
		} else {
			
			if (fieldsOfAuthorIsNull()) {
				Alerts.showFieldsInvalidAlert();
				return;
			}

			if (!yearInputIsValid()) {
				Alerts.showYearInputsInvalid();
				return;
			}

			try {
				
				Book bookUp = instantiateBook(book.getAuthor());
				
				bookService.updateBook(bookUp);
				Alerts.showSucessDataSaveAlert();
			} catch (SQLException e) {
				Alerts.showErrorAlert(e);
			}

		}

	}

	@FXML
	void btSearchAction() {
		Stage stage = new Stage();

		book = null;

		Parent fxmlViewSelectedItem;
		try {

			fxmlViewSelectedItem = FXMLLoader.load(getClass().getResource("/view/fxml/viewSelectTo.fxml"));
			Scene viewSelectedScene = new Scene(fxmlViewSelectedItem);

			stage.setScene(viewSelectedScene);
			stage.setWidth(800);
			stage.setHeight(400);
			stage.setTitle("Detalhes do livro");
			stage.getIcons().add(new Image("/view/assets/imgs/iconProgram.png"));
			stage.show();
			stage.setAlwaysOnTop(true);

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					Runnable updater = new Runnable() {
						@Override
						public void run() {
							book = (Book) viewSelectedScene.getUserData();
							if (book != null) {
								inputTitle.setText(book.getTitle());
								inputCompany.setText(book.getPublishCompany());
								inputYear.setText(book.getYear().toString());
								inputCode.setText(book.getCode());
								inputNameAuthor.setText(book.getAuthor().getName());
								selectNationality.setValue(book.getAuthor().getNationality());
								inputBiography.setText(book.getAuthor().getBiography());

								try {
									cloakBook.setImage(new Image(book.getCloak()));
								} catch (Exception e) {
									cloakBook.setImage(new Image("/view/assets/imgs/iconProgram.png"));
								}

							}
						}

					};

					while (book == null) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}

						Platform.runLater(updater);
					}

				}

			});
			thread.setDaemon(true);
			thread.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean fieldsOfBookIsNull() {
		TextField[] textFields = { inputTitle, inputCompany, inputYear, inputCode };
		for (TextField field : textFields) {
			if (field.getText() == null || field.getText().equals("")) {
				return true;
			}
		}

		if (cloakBook.getImage() == null || cloakBook.getImage().getUrl() == null) {
			return true;
		}
		return false;
	}

	private boolean fieldsOfAuthorIsNull() {
		if (inputNameAuthor.getText() == null || inputNameAuthor.getText().equals("")) {
			return true;
		}

		if (selectNationality.getValue() == null) {
			return true;
		}

		if (inputBiography.getText() == null || inputBiography.getText().equals("")) {
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

			// change to get actual year!
			if (year <= 1200 || year >= 2022) {
				return false;
			}

			return true;
		} else {
			return false;
		}

	}

	private Author instantiateAuthor() {
		Author author = new Author();
		author.setId(null);
		author.setName(inputNameAuthor.getText());
		author.setNationality(selectNationality.getValue());
		author.setBiography(inputBiography.getText());
		return author;
	}

	private Book instantiateBook(Author author) {
		Book book = null;
		book = new Book();
		book.setId(this.book.getId());
		book.setAuthor(author);
		book.setTitle(inputTitle.getText());
		book.setPublishCompany(inputCompany.getText());
		book.setCode(inputCode.getText());
		book.setYear(Integer.parseInt(inputYear.getText()));
		book.setCloak(cloakBook.getImage().getUrl());
		return book;
	}
}
