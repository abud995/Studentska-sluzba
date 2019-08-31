package model;

import java.util.Date;



public class Ispit implements Writable {

	private int id;
	private int indeks;
	private int bodovi;
	private int ocena;
	private Date date;
	private String ponisten = "false";
	private Predmet predmet;
	private IspitniRok ispitniRok;
	
	public enum IspitniRok {
		JANUARSKI, FEBRUARSKI, MARTOVSKI, APRILSKI, MAJSKI, JUNSKI, JULSKI, SEPTEMBARSKI, OKTOBARSKI, NOVEMBARSKI , DECEMBARSKI
	}


	

	public Ispit() {
		super();
	}



	public Ispit(int id, int indeks, int bodovi, int ocena, Date date, String ponisten, Predmet predmet,
			IspitniRok ispitniRok) {
		super();
		this.id = id;
		this.indeks = indeks;
		this.bodovi = bodovi;
		this.ocena = ocena;
		this.date = date;
		this.ponisten = ponisten;
		this.predmet = predmet;
		this.ispitniRok = ispitniRok;
	}



	public IspitniRok getIspitniRok() {
		return ispitniRok;
	}



	public void setIspitniRok(IspitniRok ispitniRok) {
		this.ispitniRok = ispitniRok;
	}



	public String getPonisten() {
		return ponisten;
	}



	public int getIndeks() {
		return indeks;
	}

	public void setIndeks(int indeks) {
		this.indeks = indeks;
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String isPonisten() {
		return ponisten;
	}

	public void setPonisten(String ponisten) {
		this.ponisten = ponisten;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ispit [indeks=" + getIndeks() + ", bodovi=" + bodovi + ", ocena=" + ocena + ", date=" + date
				+ ", ponisten=" + ponisten + ", predmet=" + getPredmet().getNaziv() + "]";
	}

	@Override
	public String toFile() {
		return getId() + "|" + getIndeks() + "|" + getBodovi() + "|" + getOcena() + "|" + getDate() + "|"
				+ isPonisten() + "|" + getPredmet().getNaziv() + "|" + 	getIspitniRok();
 	}

}