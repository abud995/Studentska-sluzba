package application;

import java.io.IOException;

import dataUtil.FactoryUtilsFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Student;
import utils.SearchUtils;

public class FindStudentByIndexPretragaController {
	@FXML 
	private TextField indexStudent;
	@FXML
	private Text text;
	
	@FXML
	private void find(ActionEvent event) throws IOException{
		int id = FactoryUtilsFX.checkInputInt(indexStudent.getText().trim());
		Student s = SearchUtils.findStudentById(id);
		if (s == null || s.getObrisan().equals("true")) {
			text.setText("Ne postoji student s navedenim brojem indeksa.");
			text.setFill(Color.RED);
		}
		else {
			text.setText(SearchUtils.findStudentById(id).toString());
			text.setFill(Color.BLACK);
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
