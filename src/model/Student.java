package model;




public class Student extends Korisnik {

	private String indeks;
	private String email;
	private String brtelefona;
	

	public Student(int id, String ime, String prezime, String korime, String lozinka, String obrisan, String indeks,
			String email, String brtelefona) {
		super(id, ime, prezime, korime, lozinka, obrisan);
		this.indeks = indeks;
		this.email = email;
		this.brtelefona = brtelefona;
	}
	
	
	

	public Student(int id, String ime, String prezime, String korime, String lozinka, String obrisan, String email,
			String brtelefona) {
		super(id, ime, prezime, korime, lozinka, obrisan);
		this.email = email;
		this.brtelefona = brtelefona;
	}




	public String getIndeks() {
		return indeks;
	}

	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrtelefona() {
		return brtelefona;
	}

	public void setBrtelefona(String brtelefona) {
		this.brtelefona = brtelefona;
	}

	@Override
	public String toString() {
		return "Ime: " + getIme() + "\nPrezime: " + getPrezime() + "\nID: "+ getId() +  
				"\nIndeks: " + getIndeks() + "\nE-mail: " + getEmail() +  "\nobrisan: " + isObrisan() +"\nBroj telefona: " + getBrtelefona() + "\n";
	}

	@Override
	public String toFile() {
		return getId() + "|"  + getIme() + "|" + getPrezime() + "|" + 
				getKorime() + "|" + getLozinka() +  "|" + isObrisan() + "|" + getIndeks() + "|" + getEmail() + "|" + getBrtelefona();
	}
	

}
