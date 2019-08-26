package dataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import application.App;
import model.Administrator;
import model.Indeks;
import model.Student;
import model.Nastavnik.Zvanje;
import model.Nastavnik;
import model.Predmet;
import model.Ispit;
import model.Ispit.IspitniRok;
import utils.CheckLogIn;
import utils.SearchUtils;

public class FactoryUtils {
	
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	@SuppressWarnings("deprecation")
	public static Date pocetakIspitnogRoka = new Date(117, 05, 01); 
	@SuppressWarnings("deprecation")
	public static Date krajIspitnogRoka = new Date(117, 05, 30); 

	
	public static Administrator makeAdmin(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		int id = Integer.parseInt(data[0]);
		String ime = data[1];
		String prezime = data[2];
		String korime = data[3];
		String lozinka = data[4];
		String obrisan = data[5];
		return new Administrator(id, ime, prezime, korime, lozinka, obrisan);
	}
	
	public static Nastavnik makeNastavnik(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		int id = Integer.parseInt(data[0]);
		String ime = data[1];
		String prezime = data[2];
		String korime = data[3];
		String lozinka = data[4];
		String obrisan = data[5];
		String email = data[6];
		Zvanje zvanje = Zvanje.valueOf(data[7]);
		return new Nastavnik(id, ime, prezime, korime, lozinka, obrisan, email, zvanje);
	}
	
	public static Student makeStudent(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		int id = Integer.parseInt(data[0]);
		String ime = data[1];
		String prezime = data[2];
		String korime = data[3];
		String lozinka = data[4];
		String obrisan = data[5];
		int brIndeksa = Integer.parseInt(data[6]);
		String brindeks = data[6];
		
		Indeks indeks = SearchUtils.findIndeksById(brIndeksa);
		
		String email = data[7];
		String brtelefona = data[8];

		
		return new Student(id, ime, prezime, korime, lozinka, obrisan, brindeks, email, brtelefona); 

	}
	
	public static Predmet makePredmet(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		String id = data[0];
		String naziv = data[1];
		String silabus = data[2];
		int godina = Integer.parseInt(data[3]);
		int studijskaGodina = Integer.parseInt(data[4]);
		int fondPredavanja = Integer.parseInt(data[5]);
		int fondVezbi = Integer.parseInt(data[6]);
		
		
		return new Predmet(id, naziv, silabus, godina, studijskaGodina,fondPredavanja,fondVezbi);
	}

	public static Ispit makeIspit(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		int id = Integer.parseInt(data[0]);
		int brIndeksa = Integer.parseInt(data[1]);
		
		Indeks indeks = SearchUtils.findIndeksById(brIndeksa);
		
		int bodovi = Integer.parseInt(data[2]);
		int ocena = Integer.parseInt(data[3]);
		
		Date date = null;
		try {
			date = sdf.parse(data[4]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String ponisten = data[5];
		
		
		Predmet predmet = SearchUtils.findPredmetByNaziv(data[6]);
		
		IspitniRok ispitniRok = IspitniRok.valueOf(data[7]);
		
		
		return new Ispit(id, brIndeksa, bodovi, ocena,date, ponisten,predmet, ispitniRok);
	}
	

	
	public static Indeks makeIndeks(String line) {
		if (line.equals("") || line.equals("null")){
			return null;
		}
		String[] data = line.split("\\|");
		
		int id = Integer.parseInt(data[0]);
		String univerzitet = data[1];
		String fakultet = data[2];
		String smer = data[3];
		int godinaUpisa = Integer.parseInt(data[4]);
		int godinaStudija = Integer.parseInt(data[5]);
		
		int studentId = Integer.parseInt(data[6]);
		Student student = SearchUtils.findStudentById(studentId);
		
		ArrayList<Ispit> ispiti = new ArrayList<Ispit>();
		
		String ispitiString = data[7].replace("[","").replace("]","").replace(" ","");
		String[] ispitTemp = ispitiString.split(",");
		if(ispitTemp.length>0){
			for (String s : ispitTemp){
				if(s == null)
					continue;
				ispiti.add(SearchUtils.findIspitByPredmetNaziv(s));
			}
		}
		
		return new Indeks(id,univerzitet,fakultet,smer,godinaUpisa,godinaStudija,student,ispiti);
	}
	
	
	private static Scanner imeadminScan;
	private static Scanner prezimeadminScan;
	private static Scanner korimeadminScan;
	private static Scanner lozinkaadminScan;
	
	public static void dodajAdmin(){
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String obrisan = "false";
		
		System.out.println("Podaci o administratoru:");
		
		int id = App.getAdmins().size() + 3;

		imeadminScan = new Scanner(System.in);
		System.out.println("Ime: ");
		ime = checkInput(imeadminScan.nextLine().trim());
		
		prezimeadminScan = new Scanner(System.in);
		System.out.println("Prezime: ");
		prezime = checkInput(prezimeadminScan.nextLine().trim());
		
		korimeadminScan = new Scanner(System.in);
		System.out.println("Korisnicko ime: ");
		korime = checkInputString(korimeadminScan.nextLine().trim());
		
		lozinkaadminScan = new Scanner(System.in);
		System.out.println("Lozinka: ");
		lozinka = checkInputString(lozinkaadminScan.nextLine().trim());
		

		
		ArrayList<Administrator> administratori = App.getAdmins();
		administratori.add(new Administrator(id, ime, prezime, korime, lozinka, obrisan));
		FileUtils.writeToFile(administratori, "admins");
		System.out.println("Uspesno je dodat administrator");
	}
	
	
	
	private static Scanner imeScan;
	private static Scanner prezimeScan;
	private static Scanner korimeScan;
	private static Scanner lozinkaScan;
	private static Scanner emailScan;
	private static Scanner zvanjeScan;
	
	public static void dodajNastavnika(){
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String email = null;
		Zvanje zvanje = null;
		String obrisan = "false";
		
		System.out.println("Podaci o nastavniku:");
		
		int id = App.getNastavnici().size() + 3;

		imeScan = new Scanner(System.in);
		System.out.println("Ime: ");
		ime = checkInput(imeScan.nextLine().trim());
		
		prezimeScan = new Scanner(System.in);
		System.out.println("Prezime: ");
		prezime = checkInput(prezimeScan.nextLine().trim());
		
		korimeScan = new Scanner(System.in);
		System.out.println("Korisnicko ime: ");
		korime = checkInputString(korimeScan.nextLine().trim());
		
		lozinkaScan = new Scanner(System.in);
		System.out.println("Lozinka: ");
		lozinka = checkInputString(lozinkaScan.nextLine().trim());
		
		emailScan = new Scanner(System.in);
		System.out.println("E-mail: ");
		email = checkInputString(emailScan.nextLine().trim());
		
		boolean x = true;
		Scanner typeOptionScan = new Scanner(System.in);
		while (x){
			System.out.println("-Zvanje-");
			System.out.println("Izaberite jednu od sledecih opcija:");
			System.out.println(" 1 - Saradnik");
			System.out.println(" 2 - Asistent");
			System.out.println(" 3 - Docent");
			System.out.println(" 4 - Vanredni profesor");
			System.out.println(" 5 - Redovnan profesor");
			int typeOption = FactoryUtils.checkInputInt(typeOptionScan);
			if (typeOption == 1) {
				zvanje = Zvanje.SARADNIK;
				x = false;
			}
			else if (typeOption == 2) {
				zvanje = Zvanje.ASISTENT;
				x = false;
			}
			else if (typeOption == 3) {
				zvanje = Zvanje.DOCENT;
				x = false;
			}
			else if (typeOption == 4) {
				zvanje = Zvanje.VANREDNI_PROFESOR;
				x = false;
			}
			else if (typeOption == 5) {
				zvanje = Zvanje.REDOVAN_PROFESOR;
				x = false;
			}
			else {
				System.out.println("Pogresan unos");
			}
		}
		
		ArrayList<Nastavnik> nastavnici = App.getNastavnici();
		nastavnici.add(new Nastavnik(id, ime, prezime, korime, lozinka, obrisan, email, zvanje));
		FileUtils.writeToFile(nastavnici, "nastavnici");
		System.out.println("Uspesno je dodat profesor");
	}
	
	private static Scanner nazivScan;
	private static Scanner silabusScan;

	
	public static void dodajPredmet(){
		
		
		 String id = null;
		 String naziv = null;
		 String silabus = null; 
		 int godina; // 2019
		 int studijskaGodina; // prva
		 int fondPredavanja;
		 int fondVezbi;
		
		
		System.out.println("Podaci o predmetu:");
		
		int size = App.getPredmeti().size() + 3;
		
		
		id = Integer.toString(size);
		

		nazivScan = new Scanner(System.in);
		System.out.println("Naziv: ");
		naziv = checkInputString(nazivScan.nextLine().trim());
		
		silabusScan = new Scanner(System.in);
		System.out.println("Silabus: ");
		silabus = checkInputString(silabusScan.nextLine().trim());
		
		Scanner godinaScan = new Scanner(System.in);
		System.out.println("Godina: ");
		godina = FactoryUtils.checkInputInt(godinaScan);
		
		Scanner studijskaGodinaScan = new Scanner(System.in);
		System.out.println("Studijska godina: ");
		studijskaGodina = FactoryUtils.checkInputInt(studijskaGodinaScan);

		Scanner fondPredavanjaScan = new Scanner(System.in);
		System.out.println("Fond predavanja: ");
		fondPredavanja = FactoryUtils.checkInputInt(fondPredavanjaScan);
		
		Scanner fondVezbiScan = new Scanner(System.in);
		System.out.println("Fond vezbi: ");
		fondVezbi = FactoryUtils.checkInputInt(fondVezbiScan);
		
		ArrayList<Predmet> predmeti = App.getPredmeti();
		predmeti.add(new Predmet(id, naziv, silabus, godina, studijskaGodina,fondPredavanja,fondVezbi));
		FileUtils.writeToFile(predmeti, "predmeti");
		System.out.println("Uspesno je dodat predmet");
	}
	
	private static Scanner imestScan;
	private static Scanner prezimestScan;
	private static Scanner korimestScan;
	private static Scanner lozinkastScan;
	private static Scanner indeksstScan;
	private static Scanner emailstScan;
	private static Scanner brtelefonastScan;
	
	public static void dodajStudenta(){
		
		
		String ime = null;
		String prezime = null;
		String korime = null;
		String lozinka = null;
		String obrisan = "false";
		String email = null;
		String brtelefona = null;
		Indeks indeks = null;
		String brindeks = null;
		
		
		System.out.println("Podaci o studentu:");
		
		int id = App.getStudenti().size() + 3;
		
		
		imestScan = new Scanner(System.in);
		System.out.println("Ime: ");
		ime = checkInput(imestScan.nextLine().trim());
		
		prezimestScan = new Scanner(System.in);
		System.out.println("Prezime: ");
		prezime = checkInput(prezimestScan.nextLine().trim());
		
		korimestScan = new Scanner(System.in);
		System.out.println("Korisnicko ime: ");
		korime = checkInputString(korimestScan.nextLine().trim());
		
		lozinkastScan = new Scanner(System.in);
		System.out.println("Lozinka: ");
		lozinka = checkInputString(lozinkastScan.nextLine().trim());
		
		emailstScan = new Scanner(System.in);
		System.out.println("E-mail: ");
		email = checkInputString(emailstScan.nextLine().trim());
		
		brtelefonastScan = new Scanner(System.in);
		System.out.println("Broj telefona: ");
		brtelefona = checkInputString(brtelefonastScan.nextLine().trim());
		
		indeksstScan = new Scanner(System.in);
		System.out.println("Broj indeksa: ");
		brindeks = checkInputString(indeksstScan.nextLine().trim());
		
		
//		int indeksid = App.getIndeksi().size() + 3;
//		
//		 String univerzitet = null;
//		 String fakultet = null;
//		 String smer = null;
//		 int godinaUpisa;
//		 int godinaStudija;
//		 Student studentNovi;
//		 ArrayList<Ispit> ispiti = new ArrayList<Ispit>(); 
//		
//		
//		indeks = new Indeks();
		
		ArrayList<Student> studenti = App.getStudenti();
		studenti.add(new Student(id, ime, prezime, korime, lozinka, obrisan, brindeks,email,brtelefona));
		FileUtils.writeToFile(studenti, "studenti");
		System.out.println("Uspesno je dodat student");
	}
	

	
//	public static void prijaviIspit(){
//		ArrayList<Exam> exams = App.getExams();
//		Student logedInStudent = CheckLogIn.getStudentLogedIn();
//		ArrayList<PassedSubject> passedSubjects = logedInStudent.getPassedSubjects();
//		Date date = new Date();
//		@SuppressWarnings("deprecation")
//		Date dateTemp = new Date(date.getYear(), date.getMonth(), date.getDate()+1);
//		@SuppressWarnings("deprecation")
//		Date datePayment = new Date(date.getYear(), date.getMonth(), date.getDate());
//		int i = 0;
//		for (Exam e : exams){ 
//			boolean ind = false;
//			if (e.getSubject().getSchoolYear() == logedInStudent.getSchoolYear() &&
//					e.getSubject().getDepartment().equals(logedInStudent.getDepartment()) &&
//					e.getDate().after(dateTemp)){
//				for (PassedSubject ps : passedSubjects) {
//					if (ps == null)
//						continue;
//					if(ps.getSubject().equals(e.getSubject()))
//						ind = true;
//				}
//				for (Exam e2 : logedInStudent.getExamEntry()) {
//					if (e2 == null)
//						continue;
//					if(e2.getSubject().equals(e.getSubject()))
//						ind = true;
//				}
//				if(ind)
//					continue;
//				i++;
//				System.out.println(i + ". " + e.getSubject().getName());
//			}
//		}
//		if (i == 0){
//			System.out.println("Nema ispita za prijavljivanje.");
//			return;
//		}
//		else {
//			System.out.println("Izaberite predmet koji želite polagati");
//		}
//		Scanner examOptionScan = new Scanner(System.in);
//		int examOption = FactoryUtils.checkInputInt(examOptionScan);
//		if(examOption <1 || examOption >i){
//			System.out.println("Pogrešan unos.");
//			prijaviIspit();
//		}
//		i = 0;
//		for (Exam e1 : exams){
//			boolean ind = false;
//			if (e1.getSubject().getSchoolYear() == logedInStudent.getSchoolYear() &&
//					e1.getSubject().getDepartment().equals(logedInStudent.getDepartment()) &&
//					e1.getDate().after(dateTemp)){
//				for (PassedSubject ps : passedSubjects) {
//					if (ps == null)
//						continue;
//					if(ps.getSubject().equals(e1.getSubject()))
//						ind = true;
//				}
//				for (Exam e2 : logedInStudent.getExamEntry()) {
//					if (e2 == null)
//						continue;
//					if(e2.getSubject().equals(e1.getSubject()))
//						ind = true;
//				}
//				if(ind)
//					continue;
//				i++;
//				if(i == examOption){
//					Exam selectedExam = e1;
//					if (selectedExam.getStatus().equals(Status.REGULAR)){
//						if(logedInStudent.getAccount() == 200 || logedInStudent.getAccount() > 200){
//							logedInStudent.setAccount(logedInStudent.getAccount() - 200);
//							logedInStudent.getExamEntry().add(selectedExam);
//							ArrayList<Student> students = App.getStudents();
//							FileUtils.writeToFile(students, "students");
//							ArrayList<Payment> payments = App.getPayments();
//							payments.add(new Payment(200, logedInStudent.getIndex(), datePayment, selectedExam.getSubject()));
//							FileUtils.writeToFile(payments, "payments");
//							System.out.println("Uspiješno ste prijavili ispit.");
//						}
//						else {
//							System.out.println("Ne možete prijaviti ispit, nedovoljan iznos na raèunu.");
//						}
//					}
//					else {
//						if(logedInStudent.getAccount() == 400 || logedInStudent.getAccount() > 400){
//							logedInStudent.setAccount(logedInStudent.getAccount() - 400);
//							logedInStudent.getExamEntry().add(selectedExam);
//							ArrayList<Student> students = App.getStudents();
//							FileUtils.writeToFile(students, "students");
//							ArrayList<Payment> payments = App.getPayments();
//							payments.add(new Payment(400, logedInStudent.getIndex(), datePayment, selectedExam.getSubject()));
//							FileUtils.writeToFile(payments, "payments");
//							System.out.println("Uspiješno ste prijavili ispit.");
//						}
//						else {
//							System.out.println("Ne možete prijaviti ispit, nedovoljan iznos na raèunu");
//						}
//					}
//				}
//			}
//		}
//	}
//	
//	public static void passedSubjects (){
//		Student logedInStudent = CheckLogIn.getStudentLogedIn();
//		ArrayList<PassedSubject> passedSubjects = logedInStudent.getPassedSubjects();
//		
//		int i = 0;
//		double gpa = 0;
//		double sum = 0;
//		for (PassedSubject ps : passedSubjects){
//			if(ps == null)
//				continue;
//			i++;
//			System.out.println(i + ". " + ps);
//			sum += ps.getGrade();
//		}
//		if (i == 0){
//			System.out.println("Nema položenih ispita.");
//		}
//		else {
//			gpa = sum / passedSubjects.size();
//			System.out.println("Prosjeèna ocjena: " + gpa);
//		}
//	}
//	
//	public static void examsEntry (){
//		Student logedInStudent = CheckLogIn.getStudentLogedIn();
//		ArrayList<Exam> examEntry = logedInStudent.getExamEntry();
//		ArrayList<PassedSubject> passedSubjects = logedInStudent.getPassedSubjects();
//		int i = 0;
//		Date dateNow = new Date();
//		for (Exam e : examEntry){
//			boolean ind = true;
//			boolean x = false;
//			if(e == null)
//				continue;
//			if((dateNow.after(krajIspitnogRoka) && dateNow.after(e.getDate())) ||
//					(dateNow.before(pocetakIspitnogRoka) && dateNow.before(e.getDate())))
//				x = true;
//			if(x)
//				continue;
//			i++;
//			for (PassedSubject ps : passedSubjects){
//				if (ps == null)
//					continue;
//				if(ps.getSubject().equals(e.getSubject())){
//					System.out.println(i + ". " + e.getSubject().getName() + " (Ocjena: " + ps.getGrade() + ")");
//					ind = false;
//					break;
//				}
//			}
//			if(ind)
//				System.out.println(i + ". " + e.getSubject().getName());
//		}
//		if (i == 0){
//			System.out.println("Nema prijavljenih ispita.");
//		}
//	}
//	
//	public static void payments(){
//		int i = 0;
//		for(Payment p : App.getPayments()){
//			if (p == null)
//				continue;
//			i++;
//			System.out.println(i + ". " + p);
//		}
//		if(i == 0)
//			System.out.println("Nema uplata.");
//	}
//	
//	public static void examsEntryForProfessor(){
//		Professor logedInProfessor = CheckLogIn.getProfessorLogedIn();
//		ArrayList<Payment> payments = App.getPayments();
//		int i = 0;
//		Date dateNow = new Date();
//		for (Payment p : payments){
//			boolean x = false;
//			if (p == null)
//				continue;
//			for (Exam e : SearchUtils.findStudentByIndex(p.getIndex()).getExamEntry()){
//				if(e == null)
//					continue;
//				if((dateNow.after(krajIspitnogRoka) && dateNow.after(e.getDate())) ||
//						(dateNow.before(pocetakIspitnogRoka) && dateNow.before(e.getDate())))
//					x = true;
//			}
//			if(x)
//				continue;
//			if (p.getSubject().getProfessor().getJmbg().equals(logedInProfessor.getJmbg())  &&
//					SearchUtils.findStudentByIndex(p.getIndex()).getExamEntry().contains(SearchUtils.findExamBySubject(p.getSubject().getName()))){
//				i++;
//				System.out.println(i + ". " + p.getSubject().getName() + " (" + 
//				(SearchUtils.findStudentByIndex(p.getIndex())).getFirstName() + " " + 
//						(SearchUtils.findStudentByIndex(p.getIndex())).getLastName() + " " + p.getIndex() + ")");
//			}
//		}
//		if(i == 0)
//			System.out.println("Nema prijavljenih ispita.");
//	}
//	
//	public static void marks(){
//		Professor logedInProfessor = CheckLogIn.getProfessorLogedIn();
//		ArrayList<Payment> payments = App.getPayments();
//		Date dateNow = new Date();
//		int i = 0;
//		for (Payment p : payments){
//			if (p == null)
//				continue;
//			if (p.getSubject().getProfessor().getJmbg().equals(logedInProfessor.getJmbg()) &&
//					SearchUtils.findStudentByIndex(p.getIndex()).getExamEntry().contains(SearchUtils.findExamBySubject(p.getSubject().getName()))){
//				boolean x = false;
//				for (PassedSubject ps : SearchUtils.findStudentByIndex(p.getIndex()).getPassedSubjects()){
//					if(ps == null)
//						continue;
//					if(ps.getSubject().equals(p.getSubject()))
//						x = true;
//				}
//				for (Exam e : SearchUtils.findStudentByIndex(p.getIndex()).getExamEntry()){
//					if(e == null)
//						continue;
//					if((dateNow.after(krajIspitnogRoka) && dateNow.after(e.getDate())) ||
//							(dateNow.before(pocetakIspitnogRoka) && dateNow.before(e.getDate())))
//						x = true;
//				}
//				if(x)
//					continue;
//				i++;
//				System.out.println(i + ". " + p.getSubject().getName() + " (" + 
//				(SearchUtils.findStudentByIndex(p.getIndex())).getFirstName() + " " + 
//						(SearchUtils.findStudentByIndex(p.getIndex())).getLastName() + " " + p.getIndex() + ")");
//			}
//		}
//		if(i == 0){
//			System.out.println("Nema prijavljenih ispita.");
//			return;
//		}
//		
//		System.out.println("Izaberite studenta");
//		Scanner examOptionScan = new Scanner(System.in);
//		int examOption = FactoryUtils.checkInputInt(examOptionScan);
//		if(examOption <1 || examOption >i){
//			System.out.println("Pogrešan unos.");
//			marks();
//		}
//		i = 0;
//		for (Payment p : payments){
//			if (p == null)
//				continue;
//			if (p.getSubject().getProfessor().getJmbg().equals(logedInProfessor.getJmbg())){
//				i++;
//				if(i == examOption){
//					Scanner gradeScan = new Scanner(System.in);
//					System.out.println("Ocjena: ");
//					int grade = FactoryUtils.checkInputInt(gradeScan);
//					Student s = SearchUtils.findStudentByIndex(p.getIndex());
//					PassedSubject ps = new PassedSubject(grade, p.getSubject());
//					s.getPassedSubjects().add(ps);
//					double gpa = 0;
//					double sum = 0;
//					for (PassedSubject ps1 : s.getPassedSubjects()){
//						if(ps1 == null)
//							continue;
//						sum += ps1.getGrade();
//					}
//					gpa = sum / (s.getPassedSubjects().size());
//					s.setGpa(gpa);
//					ArrayList<Student> students = App.getStudents();
//					FileUtils.writeToFile(students, "students");
//					System.out.println("Uspiješno ste unijeli ocjenu.");
//				}
//			}
//		}
//	}
//	
//	public static void odjavaIspita(){
//		Student logedInStudent = CheckLogIn.getStudentLogedIn();
//		ArrayList<Exam> exams = logedInStudent.getExamEntry();
//		ArrayList<PassedSubject> passedSubjects = logedInStudent.getPassedSubjects();
//		ArrayList<Payment> payments = App.getPayments();
//		Date date = new Date();
//		@SuppressWarnings("deprecation")
//		Date dateTemp = new Date(date.getYear(), date.getMonth(), date.getDate()+1);
//		int i = 0;
//		for (Exam e : exams){ 
//			boolean x = false;
//			if(e == null)
//				continue;
//			for (PassedSubject ps : passedSubjects) {
//				if (ps == null)
//					continue;
//				if(ps.getSubject().equals(e.getSubject()))
//					x = true;
//			}
//			if(x)
//				continue;
//			if(e.getDate().after(dateTemp)){
//				i++;
//				System.out.println(i + ". " + e.getSubject().getName());
//			}
//		}
//		if (i == 0){
//			System.out.println("Nema ispita za odjavljivanje.");
//			return;
//		}
//		else {
//			System.out.println("Izaberite ispit koji želite odjaviti");
//		}
//		Scanner examOptionScan = new Scanner(System.in);
//		int examOption = FactoryUtils.checkInputInt(examOptionScan);
//		if(examOption <1 || examOption >i){
//			System.out.println("Pogrešan unos.");
//			odjavaIspita();
//		}
//		i = 0;
//		for (Exam e1 : exams){
//			if(e1 == null)
//				continue;
//			i++;
//			if(i == examOption){
//				Exam selectedExam = e1;
//				if(selectedExam.getStatus().equals(Status.REGULAR))
//					logedInStudent.setAccount(logedInStudent.getAccount() + 200);
//				if(selectedExam.getStatus().equals(Status.NOT_REGULAR))
//					logedInStudent.setAccount(logedInStudent.getAccount() + 400);
//				logedInStudent.getExamEntry().remove(selectedExam);
//				for(Payment p : payments){
//					if(p == null)
//						continue;
//					if(p.getSubject().getName().equals(selectedExam.getSubject().getName()) && p.getIndex() == logedInStudent.getIndex()){
//						payments.remove(p);
//						FileUtils.writeToFile(payments, "payments");
//						break;
//					}
//				}
//				
//				ArrayList<Student> students = App.getStudents();
//				FileUtils.writeToFile(students, "students");
//				System.out.println("Uspiješno ste odjavili ispit.");
//				break;
//			}
//		}
//	}
	
	private static Scanner sc;
	
	public static String checkInput(String s){
		while (!s.matches("[a-zA-Z]+") || s.equals("")){
			sc = new Scanner(System.in);
			System.out.println("Greška! Unesite ponovo: ");
			s = sc.nextLine().trim();
		}
		return s;
	}
	
	private static Scanner sc2;
	
	public static String checkInputString(String s){
		while (s.equals("")){
			sc2 = new Scanner(System.in);
			System.out.println("Greška! Unesite ponovo: ");
			s = sc2.nextLine().trim();
		}
		return s;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static int checkInputInt(Scanner sc){
		String s = sc.nextLine();
		while(!isInteger(s)){
			System.out.println("Greška! Unesi ponovo: ");
			s = sc.nextLine();
		}
		int i = Integer.parseInt(s);
		return i;
	}
	
	public static boolean isDouble(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	public static double checkInputDouble(Scanner sc){
		String s = sc.nextLine();
		while(!isDouble(s)){
			System.out.println("Greška! Unesi ponovo: ");
			s = sc.nextLine();
		}
		double i = Double.parseDouble(s);
		return i;
	}

}
