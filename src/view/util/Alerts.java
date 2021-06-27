package view.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Alerts {
	

	//return true if button yes pressed
	public static boolean showOptionAuthorSave() {
		ButtonType btYes = new ButtonType("Sim", ButtonData.YES);
		ButtonType btNo = new ButtonType("N�o", ButtonData.NO);
		
		Alert alert = new Alert(AlertType.CONFIRMATION, null, btYes, btNo);
		alert.setHeaderText("Voc� est� prestes a salvar APERNAS o AUTOR. Deseja continuar?");
		alert.setTitle("Confirma��o para continuar");
		
		Optional<ButtonType> option = alert.showAndWait(); 
		
		if(option.get() == btYes) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void showSucessDataSaveAlert() {
		Alert alert = new Alert(AlertType.INFORMATION, null);
		alert.setTitle("Sucesso!");
		alert.setHeaderText("Dados gravados com sucesso!");
		alert.setContentText(null);
		alert.show();
	}
	
	public static void showFieldsInvalidAlert() {
		Alert alert = new Alert(AlertType.ERROR, null);
		alert.setTitle("Campos inv�lidos!");
		alert.setHeaderText("Preencha corretamente todos os campos!");
		alert.setContentText(null);
		alert.show();
	}
	
	public static void showYearInputsInvalid() {
		Alert alert = new Alert(AlertType.ERROR, null);
		alert.setTitle("Ano inv�lido!");
		alert.setHeaderText("Preencha corretamente o campo do ANO!");
		alert.setContentText(null);
		alert.show();
	}
	
	public static void showErrorAlert(Exception e) {
		Alert alert = new Alert(AlertType.ERROR, null);
		alert.setTitle("Erro!");
		alert.setHeaderText("Erro inesperado!: \n" + e.getMessage());
		alert.setContentText(null);
		alert.show();
	}
	
	public static void showBookExistsAlert() {
		Alert alert = new Alert(AlertType.WARNING, null);
		alert.setTitle("Esse Livro ja existe!");
		alert.setHeaderText("Esse livro ja est� cadastrado! Caso queira editar seus dados, visite a aba de edi��o.");
		alert.setContentText(null);
		alert.show();
	}
	
	//return true if button yes pressed
	public static boolean showOptionExit() {
		ButtonType btYes = new ButtonType("Sim", ButtonData.YES);
		ButtonType btNo = new ButtonType("N�o", ButtonData.NO);
		
		Alert alert = new Alert(AlertType.CONFIRMATION, null, btYes, btNo);
		alert.setHeaderText("Sair do programa?");
		alert.setTitle("Deseja realmente encerrar a aplica��o?");
		
		Optional<ButtonType> option = alert.showAndWait(); 
		
		if(option.get() == btYes) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
