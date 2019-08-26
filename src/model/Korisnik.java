package model;

public class Korisnik implements Writable {

	private int id;
	private String ime;
	private String prezime;
	private String korime;
	private String lozinka;
	private String obrisan = "false";
	
	

	public Korisnik(int id, String ime, String prezime, String korime, String lozinka, String obrisan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korime = korime;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}
	
	

	public Korisnik(String ime, String prezime, String korime, String lozinka, String obrisan) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korime = korime;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKorime() {
		return korime;
	}

	public void setKorime(String korime) {
		this.korime = korime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	

	public String getObrisan() {
		return obrisan;
	}



	public String isObrisan() {
		return obrisan;
	}

	public void setObrisan(String obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime
				+ ", korisnicko ime=" + korime + ", lozinka=" + lozinka + ", obrisan=" + obrisan + "]";
	}
	
	@Override
	public String toFile() {
		return null;
	}
	
}