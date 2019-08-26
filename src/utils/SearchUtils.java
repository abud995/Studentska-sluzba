package utils;

import application.App;
import model.Ispit;
import model.Nastavnik;
import model.Administrator;
import model.Indeks;
import model.Predmet;
import model.Student;

public class SearchUtils {

	
	public static Indeks findIndeksById(int id) {
		for (Indeks i : App.getIndeksi()) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}
	
	public static Predmet findPredmetByNaziv(String naziv) {
		for (Predmet p : App.getPredmeti()) {
			if (p.getNaziv() == naziv)
				return p;
		}
		return null;
	}
	
	public static Student findStudentById(int id) {
		for (Student s : App.getStudenti()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	public static Nastavnik findNastavnikById(int id) {
		for (Nastavnik s : App.getNastavnici()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	
	public static Ispit findIspitByPredmetNaziv(String naziv){
		for (Ispit i : App.getIspiti()) {
			
			
			Predmet p = i.getPredmet();
			
			String tempNaziv;
			try {
				tempNaziv = p.getNaziv();
				if (tempNaziv == naziv)
					return i;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				tempNaziv= "Matematika";
				if (tempNaziv == naziv)
					return i;
			}
		}
		return null;
	}
	
	
	public static Student findStudentByIndeks(String indeks){
		for (Student student : App.getStudenti()){
			if (student.getIndeks() == indeks)
				return student;
		}
		return null;
	}
	
	
	public static Administrator findAdminById(int indeks){
		for (Administrator admin : App.getAdmins()){
			if (admin.getId() == indeks)
				return admin;
		}
		return null;
	}
	
	
}
