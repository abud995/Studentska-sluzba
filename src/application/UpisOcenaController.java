package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataUtil.FactoryUtilsFX;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Indeks;
import model.Ispit;
import model.Predmet;
import model.Student;
import model.Ispit.IspitniRok;
import utils.CheckLogIn;
import utils.SearchUtils;

public class UpisOcenaController implements Initializable{
	
	ObservableList<String> rokoviList = FXCollections.observableArrayList("JANUARSKI", "FEBRUARSKI", "MARTOVSKI", "APRILSKI",
			"MAJSKI", "JUNSKI", "JULSKI", "SEPTEMBARSKI", "OKTOBARSKI", "NOVEMBARSKI" , "DECEMBARSKI");

	
	@FXML 
	private ComboBox<String> inputRok;	
	@FXML 
	private TextField inputIndeks;
	@FXML
	private TextArea showIspiti;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inputRok.setValue("JANUARSKI");
		inputRok.setItems(rokoviList);
		
		ArrayList<Ispit> ispiti = App.getIspiti();

		

	}
	
	
	@FXML
	private void find(ActionEvent event) throws IOException{
		
		IspitniRok ispitnirok;

		String typeOption = inputRok.getValue();
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
		
		ArrayList<Ispit> ispiti = App.getIspiti();
		
		String indeksStr = inputIndeks.getText();
		int indeks = Integer.parseInt(indeksStr);
		
		for (Ispit i : ispiti) {
			if (i.getIspitniRok().equals(ispitnirok) && i.getIndeks()==indeks) {
				showIspiti.appendText(i.getId() + "\n");
			}

		}
		
		String data=showIspiti.getText().trim(); //read contents of text area into 'data'
		  if(data.equals("")) {
		     showIspiti.appendText("Nema ispita");
		    }
		
	}
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("NastavnikMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}
}
