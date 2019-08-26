package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataUtil.FactoryUtilsFX;
import dataUtil.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Administrator;

public class NewAdminController {
	
	@FXML
	private TextField imeAdmin;
	@FXML
	private TextField prezimeAdmin;
	@FXML
	private TextField korimeAdmin;
	@FXML
	private TextField lozinkaAdmin;

	
	@FXML
	private void saveNewAdmin(ActionEvent event) throws IOException{
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String obrisan = "false";

		
		int id = App.getAdmins().size() + 3;
		
		
		ime = FactoryUtilsFX.checkInput(imeAdmin.getText().trim());
		if(ime == null) imeAdmin.clear();
		
		prezime = FactoryUtilsFX.checkInput(prezimeAdmin.getText().trim());
		if(prezime == null) prezimeAdmin.clear();
		
		korime = FactoryUtilsFX.checkInputString(korimeAdmin.getText().trim());
		if(korime == null) korimeAdmin.clear();
		
		lozinka = FactoryUtilsFX.checkInputString(lozinkaAdmin.getText().trim());
		if(lozinka == null) lozinkaAdmin.clear();
		

		ArrayList<Administrator> admini = App.getAdmins();
		admini.add(new Administrator(id, ime, prezime, korime, lozinka, obrisan));
		FileUtils.writeToFile(admini, "admins");
		showMessageNewAdmin();
		showAdminMenu(event);
	}
	
	private void showMessageNewAdmin() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageNewAdmin.fxml"))));
		window.showAndWait();
	}
	
	private void showError() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageError.fxml"))));
		window.showAndWait();
	}
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}


	


}
