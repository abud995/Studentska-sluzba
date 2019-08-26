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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Nastavnik;
import model.Nastavnik.Zvanje;
import model.Student;
import utils.SearchUtils;

public class PromenaNastavnikaController implements Initializable {
	
	
	ObservableList<String> zvanjaList = FXCollections.observableArrayList("Saradnik","Asistent","Docent", "Vanredni profesor", "Redovan profesor");

	
	@FXML 
	private TextField indexStudent;
	@FXML 
	private TextField promenaIme;
	@FXML 
	private TextField promenaPrezime;
	@FXML 
	private TextField promenaKorime;
	@FXML 
	private TextField promenaLozinka;
	@FXML 
	private TextField promenaEmail;
	@FXML 
	private ComboBox<String> promenaZvanje;
	@FXML
	private Text text;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		promenaZvanje.setValue("Redovan profesor");
		promenaZvanje.setItems(zvanjaList);
	}
	
	
	@FXML
	private void find(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Nastavnik s = SearchUtils.findNastavnikById(indeks);
		if (s == null || s.isObrisan().equals("true")) {
			text.setText("Ne postoji nastavnik s navedenim id");
			text.setFill(Color.RED);
		}
		else {
			text.setText(SearchUtils.findNastavnikById(indeks).toString());
			text.setFill(Color.BLACK);
		}
	}
	
	
	@FXML
	private void promeni(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Nastavnik s = SearchUtils.findNastavnikById(indeks);
		
		ArrayList<Nastavnik> nastavnici = App.getNastavnici();
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String email = null;
		Zvanje zvanje = null;
		
		for (Nastavnik st : nastavnici) {
			if (st.getId() == s.getId()) {
				
				ime = FactoryUtilsFX.checkInputString(promenaIme.getText().trim());
				prezime = FactoryUtilsFX.checkInputString(promenaPrezime.getText().trim());
				korime = FactoryUtilsFX.checkInputString(promenaKorime.getText().trim());
				lozinka = FactoryUtilsFX.checkInputString(promenaLozinka.getText().trim());
				email = FactoryUtilsFX.checkInputString(promenaEmail.getText().trim());

				String typeOption = promenaZvanje.getValue();
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
				
				
				if (!(ime == null)) {
					st.setIme(ime);
				}
				if (!(prezime == null)) {
					st.setPrezime(prezime);
				}
				if (!(korime == null)) {
					st.setKorime(korime);
				}
				if (!(lozinka == null)) {
					st.setLozinka(lozinka);
				}
				if (!(email == null)) {
					st.setEmail(email);
				}
				if (!(zvanje == null)) {
					st.setZvanje(zvanje);
				}
				


				FileUtils.writeToFile(nastavnici, "nastavnici");
			}
		}
		
		showMessagePromenaNastavnik();
		
		
	}
	
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}
	
	private void showMessagePromenaNastavnik() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessagePromenaNastavnik.fxml"))));
		window.showAndWait();
	}
	
}
