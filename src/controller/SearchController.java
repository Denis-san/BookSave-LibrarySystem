package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.entities.Author;
import model.entities.Book;
import model.services.BookService;

public class SearchController implements Initializable {

	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, String> CodeColumn;

	@FXML
	private TableColumn<Book, String> ISBNColumn;

	@FXML
	private TableColumn<Book, String> titleColumn;

	@FXML
	private TableColumn<Book, Author> authorColumn;

	@FXML
	private TableColumn<Book, Date> yearColumn;

	@FXML
	private TableColumn<Book, String> companyColumn;

	@FXML
	private TextField inputSearch;

	@FXML
	private Button btSearch;

	@FXML
	private MenuButton btAdvancedSearch;

	@FXML
	private MenuItem searchAdvancedMenu;

	private BookService service = new BookService();

	@FXML
	void advancedSearchAction(ActionEvent event) {

	}

	@FXML
	void advancedSearchMenuAction(ActionEvent event) {
	
	}

	@FXML
	void searchAction(ActionEvent event) {
		if (inputSearch.getText() == null || inputSearch.getText().equals("")) {
			tableView.setItems(listTable());
		}else {
			tableView.setItems(listSearch(inputSearch.getText()));
		}

	}

	@FXML
	void searchKeyAction() {
		tableView.setItems(listSearch(inputSearch.getText()));
	}

	
	private ObservableList<Book> listTable() {
		try {
			return FXCollections.observableArrayList(service.listAllBooks());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ObservableList<Book> listSearch(String title) {
		try {
			return FXCollections.observableArrayList(service.findByTitle(title));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		companyColumn.setCellValueFactory(new PropertyValueFactory<>("publishCompany"));

		
		tableView.setEditable(true);
		tableView.setOnMouseClicked( event -> {
			if(event.getClickCount() == 2) {
				
				Stage stage = new Stage();
				
				Parent fxmlViewSelectedItem;
				try {
					
					fxmlViewSelectedItem = FXMLLoader.load(getClass().getResource("/view/fxml/viewSelectedItem.fxml"));
					Scene viewSelectedScene = new Scene(fxmlViewSelectedItem);
					
					viewSelectedScene.setUserData(tableView.getSelectionModel().getSelectedItem());
					fxmlViewSelectedItem.setUserData(tableView.getSelectionModel().getSelectedItem());
					
					stage.setScene(viewSelectedScene);
					stage.setTitle("Detalhes do livro");
					stage.getIcons().add(new Image("/view/assets/imgs/iconProgram.png"));
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		);
		
	}

	
	
}
