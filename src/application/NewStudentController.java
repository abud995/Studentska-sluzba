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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Indeks;
import model.Ispit;
import model.Student;

public class NewStudentController {

	
	@FXML
	private TextField imeStudent;
	@FXML
	private TextField prezimeStudent;
	@FXML
	private TextField korimeStudent;
	@FXML
	private TextField lozinkaStudent;
	@FXML
	private TextField emailStudent;
	@FXML
	private TextField brtelefonaStudent;
	@FXML
	private TextField brindeksaStudent;
	@FXML
	private TextField inputUni;
	@FXML
	private TextField inputFaks;
	@FXML
	private TextField inputSmer;
	@FXML
	private TextField inputUpis;
	@FXML
	private TextField inputStudija;
	
	@FXML
	private void saveNewStudent(ActionEvent event) throws IOException{

		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String obrisan = "false";
		String email = null;
		String brtelefona = null;
		String brindeksa = null ;
		
		String univerzitet = null;
		String fakultet = null;
		String smer = null;
		int godinaStudija;
		int godinaUpisa;
		
		int id = App.getStudenti().size() + 3;
		
		
		ime = FactoryUtilsFX.checkInput(imeStudent.getText().trim());
		if(ime == null) imeStudent.clear();
		
		prezime = FactoryUtilsFX.checkInput(prezimeStudent.getText().trim());
		if(prezime == null) prezimeStudent.clear();
		
		korime = FactoryUtilsFX.checkInputString(korimeStudent.getText().trim());
		if(korime == null) korimeStudent.clear();
		
		lozinka = FactoryUtilsFX.checkInputString(lozinkaStudent.getText().trim());
		if(lozinka == null) lozinkaStudent.clear();
		
		email = FactoryUtilsFX.checkInputString(emailStudent.getText().trim());
		if(email == null) emailStudent.clear();
		
		brtelefona = FactoryUtilsFX.checkInputString(brtelefonaStudent.getText().trim());
		if(brtelefona == null) brtelefonaStudent.clear();
		
		int id2 = App.getIndeksi().size() + 3;

		String brIndeksa = Integer.toString(id2);
		
		if(ime == null || prezime == null || korime == null || lozinka == null || email == null || brtelefona == null){
			showError();
			return;
		}
		
		ArrayList<Student> studenti = App.getStudenti();
		
		Student noviStudent = new Student(id, ime, prezime, korime, lozinka, obrisan, brIndeksa, email, brtelefona);
		
		studenti.add(noviStudent);
		FileUtils.writeToFile(studenti, "studenti");
		
		
		univerzitet = FactoryUtilsFX.checkInputString(inputUni.getText().trim());
		if(univerzitet == null) inputUni.clear();
		
		fakultet = FactoryUtilsFX.checkInputString(inputFaks.getText().trim());
		if(fakultet == null) inputFaks.clear();
		
		smer = FactoryUtilsFX.checkInputString(inputSmer.getText().trim());
		if(smer == null) inputSmer.clear();
		
		godinaStudija = FactoryUtilsFX.checkInputInt(inputStudija.getText().trim());
		if(godinaStudija == 0) inputStudija.clear();
		
		godinaUpisa = FactoryUtilsFX.checkInputInt(inputUpis.getText().trim());
		if(godinaUpisa == 0) inputUpis.clear();
		
		ArrayList<Indeks> indeksi = App.getIndeksi();
		
		ArrayList<Ispit> ispiti = new ArrayList<Ispit>();
		
		ispiti = null;
		
		Indeks noviIndeks = new Indeks(id2,univerzitet,fakultet,smer,godinaUpisa,godinaStudija,noviStudent,ispiti);
		
		indeksi.add(noviIndeks);
		FileUtils.writeToFile(indeksi, "indeksi");
		
		showMessageNewStudent();
		showAdminMenu(event);
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
	
	private void showMessageNewStudent() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageNewStudent.fxml"))));
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
