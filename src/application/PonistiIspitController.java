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

public class PonistiIspitController implements Initializable{
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

	
	
	@FXML 
	private ComboBox<String> izborIspit;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

		
		ArrayList<Indeks> indeksi = App.getIndeksi();

		Student loggedInStudent = CheckLogIn.getStudentLogedIn();
		
		String studentIndeks = loggedInStudent.getIndeks();
		
		Indeks ulogovanIndeks = null;
		
		
		for (Indeks i : indeksi) {
			if (i.getStudent().getIndeks() == studentIndeks) {
				ulogovanIndeks = i;
			}
		}
		
		
		ObservableList<String> moguciIspiti =FXCollections.observableArrayList("---");

		ArrayList<Ispit> ispiti = App.getIspiti();
		
		
		for (Ispit is : ispiti) {
			if (is.getIndeks() == ulogovanIndeks.getId() && is.isPonisten().equals("false")) {
				
				
				
				String str = Integer.toString(is.getId());
				
				moguciIspiti.add(str);
				moguciIspiti.remove("---");
			}
		}
		

		
		
		izborIspit.setItems(moguciIspiti);
		

	}
		
		
	
	@FXML
	private void ponisti(ActionEvent event) throws IOException{
		
		
		ArrayList<Ispit> ispiti = App.getIspiti();
		Student loggedInStudent = CheckLogIn.getStudentLogedIn();
		
		
		String izabranIdStr = izborIspit.getValue();
		int izabranId = Integer.parseInt(izabranIdStr);
		
		for (Ispit i : ispiti) {
			if (i.getId() == izabranId) {
				i.setPonisten("true");
			}
		}
		
		
		FileUtils.writeToFile(ispiti, "ispiti");
		
		showMessagePonistenIspit();
		showStudentMenu(event);
		
	}
	
	
	private void showMessagePonistenIspit() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavještenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageOdjaviIspit.fxml"))));
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
