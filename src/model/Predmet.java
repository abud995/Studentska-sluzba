package model;

public class Predmet implements Writable {

	private String id;
	private String naziv;
	private String silabus; 
	private int godina; // 2019
	private int studijskaGodina; // prva
	private int fondPredavanja;
	private int fondVezbi;

	
	


	public Predmet(String id, String naziv, String silabus, int godina, int studijskaGodina, int fondPredavanja,
			int fondVezbi) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.silabus = silabus;
		this.godina = godina;
		this.studijskaGodina = studijskaGodina;
		this.fondPredavanja = fondPredavanja;
		this.fondVezbi = fondVezbi;
	}
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}






	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public String getSilabus() {
		return silabus;
	}



	public void setSilabus(String silabus) {
		this.silabus = silabus;
	}



	public int getGodina() {
		return godina;
	}



	public void setGodina(int godina) {
		this.godina = godina;
	}



	public int getStudijskaGodina() {
		return studijskaGodina;
	}



	public void setStudijskaGodina(int studijskaGodina) {
		this.studijskaGodina = studijskaGodina;
	}



	public int getFondPredavanja() {
		return fondPredavanja;
	}



	public void setFondPredavanja(int fondPredavanja) {
		this.fondPredavanja = fondPredavanja;
	}



	public int getFondVezbi() {
		return fondVezbi;
	}



	public void setFondVezbi(int fondVezbi) {
		this.fondVezbi = fondVezbi;
	}



	@Override
	public String toString() {
		return "Predmet id=" + id + ", naziv=" + naziv + ", silabus=" + silabus + ", godina="
				+ godina + ", studijskaGodina=" + studijskaGodina + ", fondPredavanja=" + fondPredavanja +", fondVezbi=" + fondVezbi;
	}

	@Override
	public String toFile() {
		return getId() + "|" + getNaziv() + "|" + getSilabus() + "|" + getGodina() + "|" 
				+ getStudijskaGodina() + "|" + getFondPredavanja() + "|" + getFondVezbi();				
	}

}
