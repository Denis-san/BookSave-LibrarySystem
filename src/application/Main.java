package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			System.out.println("java version: "+System.getProperty("java.version"));
			System.out.println("javafx.version: " + System.getProperty("javafx.version"));
			
			stage = primaryStage;
			
			Parent fxmlNewRegisterView = FXMLLoader.load(getClass().getResource("/view/fxml/mainTemplate.fxml"));
			Scene newRegisterViewScene = new Scene(fxmlNewRegisterView);
			
			primaryStage.setScene(newRegisterViewScene);
			primaryStage.setTitle("BookSave - Library System");
			primaryStage.getIcons().add(new Image("/view/assets/imgs/iconProgram.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeScene(Parent parent) {
		stage.setScene(new Scene(parent));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
