package model;

import java.util.ArrayList;

public class Indeks implements Writable{
	
	private int id;
	private String univerzitet;
	private String fakultet;
	private String smer;
	private int godinaUpisa;
	private int godinaStudija;
	private Student student;
	private ArrayList<Ispit> ispiti = new ArrayList<Ispit>(); 
	
	
	
	
	public Indeks(int id, String univerzitet, String fakultet, String smer, int godinaUpisa, int godinaStudija,
			Student student, ArrayList<Ispit> ispiti) {
		super();
		this.id = id;
		this.univerzitet = univerzitet;
		this.fakultet = fakultet;
		this.smer = smer;
		this.godinaUpisa = godinaUpisa;
		this.godinaStudija = godinaStudija;
		this.student = student;
		this.ispiti = ispiti;
	}

	public String getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(String univerzitet) {
		this.univerzitet = univerzitet;
	}

	public String getFakultet() {
		return fakultet;
	}

	public void setFakultet(String fakultet) {
		this.fakultet = fakultet;
	}

	public String getSmer() {
		return smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public int getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ArrayList<Ispit> getIspiti() {
		return ispiti;
	}

	public void setIspiti(ArrayList<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static ArrayList<String> ispitiToFile(ArrayList<Ispit> ispiti){
		ArrayList<String> data = new ArrayList<String>();
		if (ispiti == null) {
			data.add("");
			return data;
		}
		for (Ispit i : ispiti) {
			if (i == null)
				continue;
			data.add(i.getPredmet().getNaziv());
		}
		
		
		return data;
	}
	
	
	@Override
	public String toString() {
		return "Id :" + getId() + "\\nUniverzitet :" + getUniverzitet() + "\\nFakultet :" + getFakultet()
				+ "\\nSmer :" + getSmer() + ",\\nGodina Upisa :" + getGodinaUpisa() + ",\\nGodina Studija :" + getGodinaStudija()  
				+ "\\nStudent id :" + getStudent().getId()  + "\\nIspiti :" + ispitiToFile(getIspiti()) ;
	}
	
	@Override
	public String toFile() {
		return getId() + "|" + getUniverzitet() + "|" + getFakultet() + "|" +
				getSmer() + "|" + getGodinaUpisa() + "|" + getGodinaStudija()+ "|" +
				getStudent().getId() + "|" + ispitiToFile(getIspiti());
	}
	
	

}
