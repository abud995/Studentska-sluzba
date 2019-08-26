package model;


public class Nastavnik extends Korisnik {

	private String email;
	private Zvanje zvanje;
	
	public enum Zvanje {
		SARADNIK, ASISTENT, DOCENT, VANREDNI_PROFESOR, REDOVAN_PROFESOR
	}

	

	public Nastavnik(int id, String ime, String prezime, String korime, String lozinka, String obrisan, String email,
			Zvanje zvanje) {
		super(id, ime, prezime, korime, lozinka, obrisan);
		this.email = email;
		this.zvanje = zvanje;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Zvanje getZvanje() {
		return zvanje;
	}

	public void setZvanje(Zvanje zvanje) {
		this.zvanje = zvanje;
	}

	@Override
	public String toString() {
		return "Ime: " + getIme() + "\nPrezime: " + getPrezime() + "\nID: "+ getId() +  
				"\nE-mail: " + getEmail() +  "\nobrisan: " + isObrisan() +"\nZvanje: " + getZvanje() + "\n";
	}

	@Override
	public String toFile() {
		return getId() + "|"  + getIme() + "|" + getPrezime() + "|" + 
				getKorime() + "|" + getLozinka() +  "|" + isObrisan() + "|" + getEmail() + "|" + getZvanje();
	}

}