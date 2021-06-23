package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
	    private ComboBox<?> selectBoxNationality;

	    @FXML
	    private TextArea inputTextAreaBiography;

	    @FXML
	    private Button btCancel;

	    @FXML
	    private Button btSave;

	    @FXML
	    private ImageView shapeBookImg;
	    
	    
	    @FXML
	    protected void btTeste(ActionEvent e) throws IOException {
	    	System.out.println("Funfou!");
	    	Main.changeScene(FXMLLoader.load(getClass().getResource("/view/fxml/searchView.fxml")));
	    }
	    
	    
}
