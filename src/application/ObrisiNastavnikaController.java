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
import model.Nastavnik;
import model.Student;
import utils.SearchUtils;

public class ObrisiNastavnikaController {
	@FXML 
	private TextField indexStudent;
	@FXML
	private Text text;
	
	@FXML
	private void find(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Nastavnik s = SearchUtils.findNastavnikById(indeks);
		if (s == null || s.getObrisan().equals("true")) {
			text.setText("Ne postoji nastavnik sa navedenim id");
			text.setFill(Color.RED);
		}
		else {
			text.setText(SearchUtils.findNastavnikById(indeks).toString());
			text.setFill(Color.BLACK);
		}
	}
	
	
	@FXML
	private void obrisiNastavnika(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Nastavnik s = SearchUtils.findNastavnikById(indeks);
		
		ArrayList<Nastavnik> nastavnici = App.getNastavnici();
		
		for (Nastavnik st : nastavnici) {
			if (st.getId() == s.getId() ) {
				s.setObrisan("true");
				FileUtils.writeToFile(nastavnici, "nastavnici");
			}
		}
		
		
		showMessageObrisiNastavnika();
		
	}
	
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}
	
	private void showMessageObrisiNastavnika() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageObrisiNastavnik.fxml"))));
		window.showAndWait();
	}
	
}
