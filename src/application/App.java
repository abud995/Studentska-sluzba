package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dataUtil.FactoryUtils;
import dataUtil.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Administrator;
import model.Indeks;
import model.Ispit;
import model.Nastavnik;
import model.Student;
import utils.SearchUtils;
import model.Predmet;

public class App extends Application {

	private static ArrayList<Administrator> admins = new ArrayList<Administrator>();
	private static ArrayList<Nastavnik> nastavnici = new ArrayList<Nastavnik>();
	private static ArrayList<Student> studenti = new ArrayList<Student>();
	private static ArrayList<Ispit> ispiti = new ArrayList<Ispit>();
	private static ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
	private static ArrayList<Indeks> indeksi = new ArrayList<Indeks>();

	private Stage primaryStage;
	private AnchorPane mainLayout;

	public static ArrayList<Indeks> getIndeksi() {
		return indeksi;
	}

	public static void setIndeksi(ArrayList<Indeks> indeksi) {
		App.indeksi = indeksi;
	}

	public static ArrayList<Administrator> getAdmins() {
		return admins;
	}

	public static void setAdmins(ArrayList<Administrator> admins) {
		App.admins = admins;
	}

	public static ArrayList<Nastavnik> getNastavnici() {
		return nastavnici;
	}

	public static void setNastavnici(ArrayList<Nastavnik> nastavnici) {
		App.nastavnici = nastavnici;
	}

	public static ArrayList<Student> getStudenti() {
		return studenti;
	}

	public static void setStudenti(ArrayList<Student> studenti) {
		App.studenti = studenti;
	}

	public static ArrayList<Ispit> getIspiti() {
		return ispiti;
	}

	public static void setIspiti(ArrayList<Ispit> ispiti) {
		App.ispiti = ispiti;
	}

	public static ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public static void setPredmeti(ArrayList<Predmet> predmeti) {
		App.predmeti = predmeti;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void adminMenu() {
		System.out.println("Izaberite jednu od sledecih opcija:");
		System.out.println(" 1 - Dodaj novog studenta");
		System.out.println(" 2 - Dodaj novog nastavnika");
		System.out.println(" 3 - Dodaj novog administratora");
		System.out.println(" 4 - Obrisi studenta");
		System.out.println(" 5 - Obrisi nastavnika");
		System.out.println(" 6 - Pretraživanje studenata po prezimenu");
		System.out.println(" 7 - Pregledanje svih studenata sortiranih po prezimenu");
		System.out.println(" 8 - Izlazak iz aplikacije");
		Scanner optionScan = new Scanner(System.in);
		int option = FactoryUtils.checkInputInt(optionScan);
		if (option == 1) {
			FactoryUtils.dodajStudenta();
			adminMenu();
		}
		else if (option == 2) {
			FactoryUtils.dodajNastavnika();
			adminMenu();
		}
		else if (option == 3) {
			FactoryUtils.dodajAdmin();
			adminMenu();
		}
		
//		else if (option == 4) {
//			FactoryUtils.dodajPredmet();
//			adminMenu();
//		}
//		
//		else if (option == 5) {
//			FactoryUtils.dodajPredmet();
//			adminMenu();
//		}
//		else if (option == 5) {
//			System.out.println("Unesite broj indeksa:");
//			Scanner indexScan = new Scanner(System.in);
//			int index = FactoryUtils.checkInputInt(indexScan);
//			Student s = SearchUtils.findStudentByIndex(index);
//			if (s == null) {
//				System.out.println("Ne postoji student s navedenim brojem indeksa.");
//			}
//			else {
//				System.out.println(SearchUtils.findStudentByIndex(index));
//			}
//			adminMenu();
//		}
//		else if (option == 6) {
//			System.out.println("Unesite prezime:");
//			lastNameScan = new Scanner(System.in);
//			String lastName = FactoryUtils.checkInput(lastNameScan.nextLine());
//			String s = SearchUtils.findStudentByLastName(lastName);
//			if (s == null) {
//				System.out.println("Ne postoji student s navedenim prezimenom.");
//			}
//			else {
//				System.out.println(SearchUtils.findStudentByLastName(lastName));
//			}
//			adminMenu();
//		}
//		else if (option == 7) {
//			SearchUtils.sortStudentsByLastName();
//			adminMenu();
//		}
		else if (option == 8) {
			System.exit(0);
		}
		else {
			System.out.println("Pogrešan unos!");
			adminMenu();
		}
	}

	private void readAllData() {

		admins = FileUtils.readAdmins();
		studenti = FileUtils.readStudenti();
		nastavnici = FileUtils.readNastavnici();
		predmeti = FileUtils.readPredmeti();
		ispiti = FileUtils.readIspiti();
		indeksi = FileUtils.readIndeksi();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		readAllData();
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Studentska služba");
		showMainView();

	}

	private void showMainView() throws IOException {
		mainLayout = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		Scene scene = new Scene(mainLayout);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
