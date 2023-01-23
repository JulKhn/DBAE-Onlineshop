package data;
import java.util.Date;
import java.text.DateFormat;

/**
 * Benutzer Objekt
 * @author Julian Kuhn / Tim Fricke
 */
public class Konto {

	private String vorname;
	private String nachname;
	private String email;
	private String geburtsdatum;
	private String passwort;
	private String iban;
	private double kontostand;
	
	/**
	 * Konstruktor fuer den Benutzer
	 * @param vorname
	 * @param nachname
	 * @param email
	 * @param geburtsdatum
	 * @param benutzername
	 * @param passwort
	 * @param iban
	 * @param kontostand
	 */
	public Konto(String vorname, String nachname, String email, String geburtsdatum, String passwort, String iban, double kontostand) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.geburtsdatum = geburtsdatum;
		this.passwort = passwort;
		this.iban = iban;
		this.kontostand = kontostand;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getEmail() {
		return email;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public String getPasswort() {
		return passwort;
	}

	public String getIban() {
		return iban;
	}
	
	public double getKontostand() {
		return kontostand;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}
	
}
