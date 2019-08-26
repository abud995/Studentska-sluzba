package application;

import java.io.IOException;
import java.util.ArrayList;

import dataUtil.FactoryUtilsFX;
import dataUtil.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Student;
import utils.SearchUtils;

public class PromenaStudentaController {
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
	private TextField promenaBrtelefona;
	@FXML 
	private TextField promenaBrindeksa;
	@FXML
	private Text text;
	
	@FXML
	private void find(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Student s = SearchUtils.findStudentById(indeks);
		if (s == null || s.isObrisan().equals("true")) {
			text.setText("Ne postoji student s navedenim id");
			text.setFill(Color.RED);
		}
		else {
			text.setText(SearchUtils.findStudentById(indeks).toString());
			text.setFill(Color.BLACK);
		}
	}
	
	
	@FXML
	private void promeni(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Student s = SearchUtils.findStudentById(indeks);
		
		ArrayList<Student> studenti = App.getStudenti();
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String email = null;
		String brtelefona = null;
		String brindeksa = null;
		
		for (Student st : studenti) {
			if (st.getId() == s.getId()) {
				
				ime = FactoryUtilsFX.checkInputString(promenaIme.getText().trim());
				prezime = FactoryUtilsFX.checkInputString(promenaPrezime.getText().trim());
				korime = FactoryUtilsFX.checkInputString(promenaKorime.getText().trim());
				lozinka = FactoryUtilsFX.checkInputString(promenaLozinka.getText().trim());
				email = FactoryUtilsFX.checkInputString(promenaEmail.getText().trim());
				brtelefona = FactoryUtilsFX.checkInputString(promenaBrtelefona.getText().trim());
				brindeksa = FactoryUtilsFX.checkInputString(promenaBrindeksa.getText().trim());
				
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
				if (!(brtelefona == null)) {
					st.setBrtelefona(brtelefona);
				}
				if (!(brindeksa == null)) {
					st.setIndeks(brindeksa);
				}

				FileUtils.writeToFile(studenti, "studenti");
			}
		}
		
		showMessagePromenaStudent();
	}
	
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}
	
	private void showMessagePromenaStudent() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessagePromenaStudent.fxml"))));
		window.showAndWait();
	}
	
	
}
