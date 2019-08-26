package utils;

import application.App;
import model.Administrator;
import model.Nastavnik;
import model.Student;

public class CheckLogIn {
	
	private static Administrator adminLogedIn;
	private static Student studentLogedIn;
	private static Nastavnik nastavnikLogedIn;
	
	public static boolean checkLogIn(String korisnickoIme, String lozinka){
		for (Administrator admin : App.getAdmins()){
			if (admin.getKorime().equals(korisnickoIme) && admin.getLozinka().equals(lozinka) && admin.isObrisan().equals("false")){
				adminLogedIn = admin;
				return false;
			}
		}
		
		
		for (Nastavnik nastavnik : App.getNastavnici()){
			if (nastavnik.getKorime().equals(korisnickoIme) && nastavnik.getLozinka().equals(lozinka) && nastavnik.isObrisan().equals("false")){
				nastavnikLogedIn = nastavnik;
				return false;
			}
		}
		
		for (Student student : App.getStudenti()){
			if (student.getKorime().equals(korisnickoIme) && student.getLozinka().equals(lozinka) && student.isObrisan().equals("false")){
				studentLogedIn = student;
				return false;
			}
		}
		
		return true;
	}

	public static Administrator getAdministratorLogedIn() {
		return adminLogedIn;
	}

	public static void setAdministratorLogedIn(Administrator adminLogedIn) {
		CheckLogIn.adminLogedIn = adminLogedIn;
	}

	public static Student getStudentLogedIn() {
		return studentLogedIn;
	}

	public static void setStudentLogedIn(Student studentLogedIn) {
		CheckLogIn.studentLogedIn = studentLogedIn;
	}

	public static Nastavnik getNastavnikLogedIn() {
		return nastavnikLogedIn;
	}

	public static void setNastavnikLogedIn(Nastavnik nastavnikLogedIn) {
		CheckLogIn.nastavnikLogedIn = nastavnikLogedIn;
	}

}
