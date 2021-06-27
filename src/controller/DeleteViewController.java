package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.entities.Book;
import model.services.BookService;
import view.util.Alerts;

public class DeleteViewController implements Initializable {

	@FXML
	private BorderPane pane;

	@FXML
	private Button btSearch;

	@FXML
	private Button btCancel;

	@FXML
	private Button btDelete;

	@FXML
	private ImageView cloakBook;

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
	private TextField inputNationality;

	@FXML
	private TextArea inputBiography;
	
	private Book book = null;

	@FXML
	void btSearchAction() {
		Stage stage = new Stage();

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

			new Thread(() -> {

				while (viewSelectedScene.getWindow().isShowing()) {
					book = (Book) viewSelectedScene.getUserData();
					if (book != null) {

						inputTitle.setText(book.getTitle());
						inputCompany.setText(book.getPublishCompany());
						inputYear.setText(book.getYear().toString());
						inputCode.setText(book.getCode());
						inputISBN.setText("TXT MUDE");
						inputNameAuthor.setText(book.getAuthor().getName());
						inputNationality.setText(String.valueOf(book.getAuthor().getNationality()));
						inputBiography.setText(book.getAuthor().getBiography());
						cloakBook.setImage(new Image(book.getCloak()));

						Thread.currentThread().stop();
						break;
					}
				}
			}).start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btCancelAction() {
		TextField[] textFields = { inputTitle, inputCompany, inputYear, inputCode, inputISBN, inputNameAuthor };
		for (TextField field : textFields) {
			field.setText(null);
		}

		inputBiography.setText(null);
		inputNationality.setText(null);
		cloakBook.setImage(new Image("/view/assets/imgs/template-img.png"));
	}

	@FXML
	void btDeleteAction() {
		BookService service = new BookService();
		try {
		if(Alerts.showOptionDelete()) {
			service.deleteBook(book.getId());
			Alerts.showSucessDataSaveAlert();
		}
		}catch(SQLException e) {
			Alerts.showErrorAlert(e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btCancel.setOnMouseEntered(event -> {
			changeBorderButton(btCancel, true);
		});

		btCancel.setOnMouseExited(event -> {
			changeBorderButton(btCancel, false);
		});
		
		btDelete.setOnMouseEntered(event -> {
			changeBorderButton(btDelete, true);
		});

		btDelete.setOnMouseExited(event -> {
			changeBorderButton(btDelete, false);
		});
		
		btSearch.setOnMouseEntered(event -> {
			changeBorderButton(btSearch, true);
		});

		btSearch.setOnMouseExited(event -> {
			changeBorderButton(btSearch, false);
		});
	}

	private void changeBorderButton(Button bt, boolean active) {
		if (active) {
			bt.setStyle("-fx-background-radius: 50; -fx-background-color: #f2f2f2;");
		}else {
			bt.setStyle("-fx-background-radius: none; -fx-background-color: white;");
		}
	}
}
