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
import model.Nastavnik;
import model.Predmet;
import model.Student;
import utils.SearchUtils;
import java.util.Random;


public class NewPredmetController {
	@FXML
	private TextField nazivPredmet;
	@FXML
	private TextField silabusPredmet;
	@FXML
	private TextField godinaPredmet;
	@FXML
	private TextField stgodPredmet;
	@FXML
	private TextField predavanjaPredmet;
	@FXML
	private TextField vezbePredmet;
	
	@FXML
	private void savePredmet(ActionEvent event) throws IOException{
		
		String naziv = null;
		String silabus = null;
		int godina;
		int stGodina;
		int fondVezbi;
		int fondPredavanja;

		
//		String id = App.getPredmeti().size() + "3";
		
		
		naziv = FactoryUtilsFX.checkInput(nazivPredmet.getText().trim());
		if(naziv == null) nazivPredmet.clear();
		
		silabus = FactoryUtilsFX.checkInput(silabusPredmet.getText().trim());
		if(silabus == null) silabusPredmet.clear();
		
		godina = FactoryUtilsFX.checkInputInt(godinaPredmet.getText().trim());
		if(godina == 0) godinaPredmet.clear();
		
		stGodina = FactoryUtilsFX.checkInputInt(stgodPredmet.getText().trim());
		if(stGodina == 0) stgodPredmet.clear();
		
		fondPredavanja = FactoryUtilsFX.checkInputInt(predavanjaPredmet.getText().trim());
		if(fondPredavanja == 0) predavanjaPredmet.clear();
		
		fondVezbi = FactoryUtilsFX.checkInputInt(vezbePredmet.getText().trim());
		if(fondVezbi == 0) vezbePredmet.clear();

		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		char c2 = (char)(r.nextInt(26) + 'a');
		String idPrvo = new StringBuilder().append(c).append(c2).toString();

		String idDrugo = String.valueOf(godina).substring(2);
		
		String id = idPrvo + idDrugo;
		
		
		
		ArrayList<Predmet> predmeti = App.getPredmeti();
		predmeti.add(new Predmet(id, naziv, silabus, godina, stGodina, fondPredavanja, fondVezbi));
		FileUtils.writeToFile(predmeti, "predmeti");
		showMessageNewPredmet();
		showAdminMenu(event);
	}
	
	
	private void showMessageNewPredmet() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavještenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageNewPredmet.fxml"))));
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
