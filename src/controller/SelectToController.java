package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Author;
import model.entities.Book;
import model.services.BookService;

public class SelectToController implements Initializable {

	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, String> CodeColumn;

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

	private BookService service = null;

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
		} else {
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

		service = new BookService();
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		CodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
		companyColumn.setCellValueFactory(new PropertyValueFactory<>("publishCompany"));

		tableView.setEditable(true);
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				tableView.getScene().setUserData(tableView.getSelectionModel().getSelectedItem());
				tableView.getScene().getWindow().hide();
			}
		});

	}

}
