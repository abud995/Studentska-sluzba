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

public class FindStudentByIndexController {
	@FXML 
	private TextField indexStudent;
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
	private void obrisi(ActionEvent event) throws IOException{
		int indeks = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Student s = SearchUtils.findStudentById(indeks);
		
		ArrayList<Student> studenti = App.getStudenti();
		
		for (Student st : studenti) {
			if (st.getId() == s.getId()) {
				s.setObrisan("true");
				FileUtils.writeToFile(studenti, "studenti");
			}
		}
		
		
		showMessageObrisiStudent();
		
	}
	
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.show();
	}
	
	private void showMessageObrisiStudent() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavestenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageObrisiStudent.fxml"))));
		window.showAndWait();
	}
	
	
}
