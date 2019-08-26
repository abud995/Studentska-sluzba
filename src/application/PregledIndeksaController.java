package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataUtil.FactoryUtilsFX;
import dataUtil.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Indeks;
import model.Ispit;
import model.Student;
import utils.CheckLogIn;
import utils.SearchUtils;

public class PregledIndeksaController implements Initializable {

	@FXML
	private Label showIdIndeksa;
	@FXML
	private Label showUniverzitet;
	@FXML
	private Label showFakultet;
	@FXML
	private Label showSmer;
	@FXML
	private Label showGodinaUpisa;
	@FXML
	private Label showStudijskaGodina;
	@FXML
	private Label showStudentId;
	@FXML
	private Label showIspiti;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Student loggedInStudent = CheckLogIn.getStudentLogedIn();
		
		ArrayList<Indeks> indeksi = App.getIndeksi();		
		
		Indeks loggedInIndeks = null;
		
		for (Indeks i : indeksi) {
			if(i.getStudent() ==loggedInStudent) {
				loggedInIndeks = i;
				break;
			}
		}
		
		String brind = Integer.toString(loggedInIndeks.getId());
		showIdIndeksa.setText(brind);
		
		showUniverzitet.setText(loggedInIndeks.getUniverzitet());
		
		showFakultet.setText(loggedInIndeks.getFakultet());
		
		showSmer.setText(loggedInIndeks.getSmer());
		
		String godup = Integer.toString(loggedInIndeks.getGodinaUpisa());
		showGodinaUpisa.setText(godup);
		
		String studgod = Integer.toString(loggedInIndeks.getGodinaStudija());
		showStudijskaGodina.setText(studgod);
		
		String stId = Integer.toString(loggedInIndeks.getStudent().getId());
		showStudentId.setText(stId);

		String indeksString = loggedInIndeks.toFile();		
		String[] data = indeksString.split("\\|");
		
		showIspiti.setText(data[7]);
		
	}
	
	
	
	@FXML
	private void showStudentMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("StudentMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}



}
