package dataUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Administrator;
import model.Indeks;
import model.Student;
import model.Nastavnik;
import model.Predmet;
import model.Ispit;
import model.Writable;

public class FileUtils {
	
	private static ArrayList<String> readLines(String filename) {

		try {
			String path = getRelativePath(filename);
			File file = new File(path);
			if (!file.exists()) {
				System.out.println("File '" + filename + "' does not exist!");
				return null;
			}

			BufferedReader in = new BufferedReader(new FileReader(path));
			String line;
			ArrayList<String> ret = new ArrayList<>();
			while ((line = in.readLine()) != null) { 
				ret.add(line.trim());
			}
			in.close();
			return ret;

		} catch (Exception e) {
			System.out.println("Something went wrong with file " + filename + "!");
			e.printStackTrace();
			return null;
		}
	}

	public static String getRelativePath(String fileName) {
		return "src" + File.separator + "data" + File.separator + fileName + ".txt";
	}


	public static void writeToFile(ArrayList<? extends Writable> writable, String filename) {
		String path = getRelativePath(filename);
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(path));
			for (Writable w : writable)
				out.println(w.toFile());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Administrator> readAdmins() {
		ArrayList<Administrator> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("admins");
		for (String line : lines) {
			ret.add(FactoryUtils.makeAdmin(line));
		}
		return ret;
	}

	public static ArrayList<Nastavnik> readNastavnici() {
		ArrayList<Nastavnik> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("nastavnici");
		for (String line : lines) {
			ret.add(FactoryUtils.makeNastavnik(line));
		}
		return ret;
	}

	public static ArrayList<Predmet> readPredmeti() {
		ArrayList<Predmet> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("predmeti");
		for (String line : lines) {
			ret.add(FactoryUtils.makePredmet(line));
		}
		return ret;
	}
	
	public static ArrayList<Student> readStudenti() {
		ArrayList<Student> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("studenti");
		for (String line : lines) {
			ret.add(FactoryUtils.makeStudent(line));
		}
		return ret;
	}
	
	
	public static ArrayList<Ispit> readIspiti() {
		ArrayList<Ispit> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("ispiti");
		for (String line : lines) {
			ret.add(FactoryUtils.makeIspit(line));
		}
		return ret;
	}

	public static ArrayList<Indeks> readIndeksi() {
		ArrayList<Indeks> ret = new ArrayList<>();
		ArrayList<String> lines = readLines("indeksi");
		for (String line : lines) {
			ret.add(FactoryUtils.makeIndeks(line));
		}
		return ret;
	}

}
