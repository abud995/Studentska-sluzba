package application;

import java.io.IOException;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.CheckLogIn;

public class Controller {

	
	@FXML
	private void checkLogIn(ActionEvent event) throws IOException{
		String korisnickoIme;
		String lozinka;
		
		korisnickoIme = usernameLogIn.getText().trim();
		lozinka = passwordLogIn.getText().trim();
		
		if (CheckLogIn.checkLogIn(korisnickoIme, lozinka)){
			usernameLogIn.clear();
			passwordLogIn.clear();
			warning.setVisible(true);
			return;
		}
		
		if (CheckLogIn.getAdministratorLogedIn() != null){
			showAdminMenu(event);
		}
		
		if (CheckLogIn.getStudentLogedIn() != null){
			showStudentMenu(event);
		}
		
		if (CheckLogIn.getNastavnikLogedIn() != null){
			showNastavnikMenu(event);
		}
	}
	
	
	@FXML
	private void showAdminMenu(ActionEvent event) throws IOException{
		Parent adminMenu = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
		Scene scene = new Scene(adminMenu);
		Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		adminStage.setScene(scene);
		adminStage.setTitle("Dobro došli, " + CheckLogIn.getAdministratorLogedIn().getIme().toUpperCase() + " " +
				CheckLogIn.getAdministratorLogedIn().getPrezime().toUpperCase());
		adminStage.show();
	}
	
	@FXML
	private void showStudentMenu(ActionEvent event) throws IOException{
		Parent studentMenu = FXMLLoader.load(getClass().getResource("StudentMenu.fxml"));
		Scene scene = new Scene(studentMenu);
		Stage studentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		studentStage.setScene(scene);
		studentStage.setTitle("Dobro došli, " + CheckLogIn.getStudentLogedIn().getIme().toUpperCase() + " " +
				CheckLogIn.getStudentLogedIn().getPrezime().toUpperCase());
		studentStage.show();
	}
	
	@FXML
	private void showNastavnikMenu(ActionEvent event) throws IOException{
		Parent professorMenu = FXMLLoader.load(getClass().getResource("NastavnikMenu.fxml"));
		Scene scene = new Scene(professorMenu);
		Stage professorStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		professorStage.setScene(scene);
		professorStage.setTitle("Dobro došli, " + CheckLogIn.getNastavnikLogedIn().getIme().toUpperCase() + " " +
				CheckLogIn.getNastavnikLogedIn().getPrezime().toUpperCase());
		professorStage.show();
	} 
	
	@FXML
	private void exit(){
		Platform.exit();
	}
	
	@FXML 
	private TextField usernameLogIn;
	@FXML
	private TextField passwordLogIn;
	@FXML 
	private Text warning;
	
	
	@FXML 
	private void newAdmin(ActionEvent event) throws IOException{
		Parent newAdmin = FXMLLoader.load(getClass().getResource("NewAdmin.fxml"));
		Scene scene = new Scene(newAdmin);
		Stage newAdminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		newAdminStage.setScene(scene);
		newAdminStage.show();
	}
	
	@FXML 
	private void newProfessor(ActionEvent event) throws IOException{
		Parent newProfessor = FXMLLoader.load(getClass().getResource("NewNastavnik.fxml"));
		Scene scene = new Scene(newProfessor);
		Stage newProfessorStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		newProfessorStage.setScene(scene);
		newProfessorStage.show();
	}
	
	@FXML
	private void newSubject(ActionEvent event) throws IOException{
		if(App.getNastavnici().isEmpty()){
			showMessage();
			showAdminMenu(event);
			return;
		}
		Parent newSubject = FXMLLoader.load(getClass().getResource("NewSubject.fxml"));
		Scene scene = new Scene(newSubject);
		Stage newSubjectStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		newSubjectStage.setScene(scene);
		newSubjectStage.show();
	}
	
	@FXML 
	private void newStudent(ActionEvent event) throws IOException{
		Parent newStudent = FXMLLoader.load(getClass().getResource("NewStudent.fxml"));
		Scene scene = new Scene(newStudent);
		Stage newStudentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		newStudentStage.setScene(scene);
		newStudentStage.show();
	}
	
	@FXML 
	private void obrisiStudent(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("FindStudentByIndex.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void obrisiNastavnika(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("ObrisiNastavnika.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void promenaStudenta(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("PromenaStudenta.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void promenaNastavnika(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("PromenaNastavnika.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void newPredmet(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("NewPredmet.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void pretragaStudenata(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("FindStudentByIndexPretraga.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void upisOcena(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("UpisOcena.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void findStudentByIndex(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("FindStudentByIndex.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void ponistiIspit(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("PonistiIspit.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void mark(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("Marks.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void findStudentsByLastName(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("FindStudentsByLastName.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void prijaviIspit(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("PrijaviIspit.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void examsEntry(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("ExamsEntry.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	@FXML 
	private void pregledIndeksa(ActionEvent event) throws IOException{
		Parent p = FXMLLoader.load(getClass().getResource("PregledIndeksa.fxml"));
		Scene scene = new Scene(p);
		Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
		s.setScene(scene);
		s.show();
	}
	
	
	private void showMessage() throws IOException{
		final Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		window.setTitle("Obavještenje");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MessageNewSubjectError.fxml"))));
		window.showAndWait();
	}
	

}
