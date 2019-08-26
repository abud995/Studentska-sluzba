package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Indeks;
import model.Ispit;
import model.Ispit.IspitniRok;
import model.Nastavnik.Zvanje;
import model.Predmet;
import model.Student;
import utils.CheckLogIn;
import utils.SearchUtils;

public class PrijaviIspitController implements Initializable{
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

	
	ObservableList<String> rokoviList = FXCollections.observableArrayList("JANUARSKI", "FEBRUARSKI", "MARTOVSKI", "APRILSKI",
			"MAJSKI", "JUNSKI", "JULSKI", "SEPTEMBARSKI", "OKTOBARSKI", "NOVEMBARSKI" , "DECEMBARSKI");

	
	@FXML 
	private ComboBox<String> izborPredmet;
	@FXML 
	private ComboBox<String> izborRok;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		izborRok.setValue("JANUARSKI");
		izborRok.setItems(rokoviList);
		
		ArrayList<Ispit> ispiti = App.getIspiti();
		Student loggedInStudent = CheckLogIn.getStudentLogedIn();
		
		String studentGodina = loggedInStudent.getIndeks();
		
		ArrayList<Indeks> indeksi = App.getIndeksi();		
		
		Indeks loggedInIndeks = null;
		
		for (Indeks i : indeksi) {
			if(i.getStudent() ==loggedInStudent) {
				loggedInIndeks = i;
				break;
			}
		}
		
		ArrayList<Predmet> predmeti = App.getPredmeti();
		
		ObservableList<String> moguciPredmeti =FXCollections.observableArrayList("---");
		
		for (Predmet p : predmeti) {
			if (p.getStudijskaGodina() == loggedInIndeks.getGodinaStudija()) {
				moguciPredmeti.add(p.getNaziv());
				moguciPredmeti.remove("---");
			}
		}
		
		izborPredmet.setItems(moguciPredmeti);
		

	}
		
		
	
	@FXML
	private void prijavi(ActionEvent event) throws IOException{
		
		
		ArrayList<Ispit> ispiti = App.getIspiti();
		Student loggedInStudent = CheckLogIn.getStudentLogedIn();

		
		int id = App.getIspiti().size() + 3;
		
		String indeksString = loggedInStudent.getIndeks();
		int indeks = Integer.parseInt(indeksString);			

		int bodovi = 0;
		int ocena = 0;
		
		Date date = null;
        try {
			 date  = sdf.parse("01.01.2001.");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		String predmetNaziv = izborPredmet.getValue();
        
		String ponisten = "false";
		Predmet predmet = SearchUtils.findPredmetByNaziv(predmetNaziv);
		
		IspitniRok ispitnirok;

		String typeOption = izborRok.getValue();
		if (typeOption == "JANUARSKI") {
			ispitnirok = IspitniRok.JANUARSKI;
		}
		else if (typeOption == "FEBRUARSKI") {
			ispitnirok = IspitniRok.FEBRUARSKI;
		}
		else if (typeOption == "FEBRUARSKI") {
			ispitnirok = IspitniRok.FEBRUARSKI;
		}
		else if (typeOption == "MARTOVSKI") {
			ispitnirok = IspitniRok.MARTOVSKI;
		}
		else if (typeOption == "APRILSKI") {
			ispitnirok = IspitniRok.APRILSKI;
		}
		else if (typeOption == "MAJSKI") {
			ispitnirok = IspitniRok.MAJSKI;
		}
		else if (typeOption == "JUNSKI") {
			ispitnirok = IspitniRok.JUNSKI;
		}
		else if (typeOption == "JULSKI") {
			ispitnirok = IspitniRok.JULSKI;
		}
		else if (typeOption == "SEPTEMBARSKI") {
			ispitnirok = IspitniRok.SEPTEMBARSKI;
		}
		else if (typeOption == "OKTOBARSKI") {
			ispitnirok = IspitniRok.OKTOBARSKI;
		}
		else if (typeOption == "NOVEMBARSKI") {
			ispitnirok = IspitniRok.NOVEMBARSKI;
		}
		else {
			ispitnirok = IspitniRok.DECEMBARSKI;
		}

		
		Ispit noviIspit = new Ispit(id, indeks, bodovi, ocena, date, ponisten, predmet, ispitnirok);
		
		ispiti.add(noviIspit);
		FileUtils.writeToFile(ispiti, "ispiti");
		
		ArrayList<Indeks> indeksi = App.getIndeksi();

		for (Indeks i : indeksi) {
			if (i.getId() == loggedInStudent.getId()) {
				ArrayList<Ispit> trenutniIspiti = i.getIspiti();
				trenutniIspiti.add(noviIspit);
				i.setIspiti(trenutniIspiti);

			}
		}
		
		FileUtils.writeToFile(indeksi, "indeksi");
		
		showMessageNewIspit();
		showStudentMenu(event);
		
	}
	
	
	private void showMessageNewIspit() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavještenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessagePrijaviIspit.fxml"))));
		window.showAndWait();
	}
	


	
	@FXML
	private void showStudentMenu(ActionEvent event) throws IOException{
		Parent studentMenu = FXMLLoader.load(getClass().getResource("StudentMenu.fxml"));
		Scene scene = new Scene(studentMenu);
		Stage studentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		studentStage.setScene(scene);
		studentStage.show();
	}
}
