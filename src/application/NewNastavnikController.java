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
import model.Nastavnik;
import model.Nastavnik.Zvanje;


public class NewNastavnikController implements Initializable {
	ObservableList<String> zvanjaList = FXCollections.observableArrayList("Saradnik","Asistent","Docent", "Vanredni profesor", "Redovan profesor");
	
	@FXML
	private TextField imeNastavnik;
	@FXML
	private TextField prezimeNastavnik;
	@FXML
	private TextField korimeNastavnik;
	@FXML
	private TextField lozinkaNastavnik;
	@FXML
	private TextField emailNastavnik;
	@FXML 
	private ComboBox<String> zvanjeNastavnik;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		zvanjeNastavnik.setValue("Redovan profesor");
		zvanjeNastavnik.setItems(zvanjaList);
	}
	
	
	@FXML
	private void saveNewNastavnik(ActionEvent event) throws IOException{
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String obrisan = "false";
		String email = null;
		Zvanje zvanje = null;
		
		int id = App.getNastavnici().size() + 3;
		
		
		ime = FactoryUtilsFX.checkInput(imeNastavnik.getText().trim());
		if(ime == null) imeNastavnik.clear();
		
		prezime = FactoryUtilsFX.checkInput(prezimeNastavnik.getText().trim());
		if(prezime == null) prezimeNastavnik.clear();
		
		korime = FactoryUtilsFX.checkInputString(korimeNastavnik.getText().trim());
		if(korime == null) korimeNastavnik.clear();
		
		lozinka = FactoryUtilsFX.checkInputString(lozinkaNastavnik.getText().trim());
		if(lozinka == null) lozinkaNastavnik.clear();
		
		email = FactoryUtilsFX.checkInputString(emailNastavnik.getText().trim());
		if(email == null) emailNastavnik.clear();
		
		String typeOption = zvanjeNastavnik.getValue();
		if (typeOption == "Saradnik") {
			zvanje = Zvanje.SARADNIK;
		}
		else if (typeOption == "Asistent") {
			zvanje = Zvanje.ASISTENT;
		}
		else if (typeOption == "Docent") {
			zvanje = Zvanje.DOCENT;
		}
		else if (typeOption == "Vanredni profesor") {
			zvanje = Zvanje.VANREDNI_PROFESOR;
		}
		else {
			zvanje = Zvanje.REDOVAN_PROFESOR;
		}
		
		if(ime == null || prezime == null || korime == null || lozinka == null || email == null ){
			showError();
			return;
		}
		ArrayList<Nastavnik> nastavnici = App.getNastavnici();
		nastavnici.add(new Nastavnik(id, ime, prezime, korime, lozinka, obrisan, email, zvanje));
		FileUtils.writeToFile(nastavnici, "nastavnici");
		showMessageNewNastavnik();
		showAdminMenu(event);
	}
	
	private void showMessageNewNastavnik() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageNewNastavnik.fxml"))));
		window.showAndWait();
	}
	
	private void showError() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavještenje");
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
