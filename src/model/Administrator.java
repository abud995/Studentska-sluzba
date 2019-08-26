package model;

public class Administrator extends Korisnik {

	
	
	public Administrator(int id, String ime, String prezime, String korime, String lozinka, String obrisan) {
		super(id, ime, prezime, korime, lozinka, obrisan);
		// TODO Auto-generated constructor stub
	}

	public Administrator(String ime, String prezime, String korime, String lozinka, String obrisan) {
		super(ime, prezime, korime, lozinka, obrisan);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toFile() {
		return getId() + "|"  + getIme() + "|" + getPrezime() + "|" + 
				getKorime() + "|" + getLozinka() + "|" + isObrisan() ;
	}
	
}
