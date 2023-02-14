package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import data.Konto;

/**
 * Klasse zum erstellen eines Kontos
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class KontoDatabase {

private static Connection con = null;
	
	/**
	 * Hier soll mit den eingegebenen Daten in der Datenbank ein neues Konto hinzugefuegt werden.
	 * Fuer die Kontodaten und das Passwort gibt es dabei zwei Tabellen
	 * @param konto
	 * @return erfolg
	 */
	public static boolean kontoEinfuegen(Konto konto) {
		boolean erfolg = false;
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO kundenkonto VALUES (" +
					"nextval('konto_id_seq'), "+		//kontoid
					"?, "+		//vorname
					"?, "+		//nachname
					"?, "+		//email
					"?, "+		//geburtsdatum
					"?, "+		//iban
					"? "+		//kontostand
					")" );
			pstmt.setString(1, konto.getVorname());
			pstmt.setString(2, konto.getNachname());
			pstmt.setString(3, konto.getEmail());
			pstmt.setString(4, konto.getGeburtsdatum());
			pstmt.setString(5, konto.getIban());
			pstmt.setDouble(6, konto.getKontostand());
			pstmt.executeUpdate();
			
			//Passwort des Kontoss verschluesselt in eigener Tabelle speichern
			PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO passwort (kontoid, passwort) VALUES (" +
					"(SELECT kontoid FROM kundenkonto WHERE email = ?), " +
					"? )"); 		//passwort
			
			//Encoden des Passworts, sodass es verschlüsselt in der Datenbank steht. Base64 als standard-Verschlüsselung
			byte[] passwort = konto.getPasswort().getBytes();
			passwort = Base64.getEncoder().encode(passwort);
			String passwortencoded = new String(passwort);
			
			pstmt2.setString(1, konto.getEmail());
			pstmt2.setString(2, passwortencoded);
			pstmt2.executeUpdate();
			erfolg = true;
			
					
		} catch (SQLException e){
			System.out.println("SQLException cought -> Problem bei der Registrierung " + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return erfolg;
	}
	
	/**
	 * Bei dieser Methode wird das Passwort des Kontos anhand der Kontoid ermittelt.
	 * @param konto
	 * @param passwortencoded
	 * @return Boolean
	 */
	public static boolean kontoPasswort(Konto konto, String passwortencoded) {
		boolean erfolg = false;
		
		try {
			con = DatabaseConnection.getConnection();
			
			//Passwort und KontoID aus der DB mit Eingabe abgleichen
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM passwort WHERE passwort = ? AND kontoid = ?");
			pstmt.setString(1, passwortencoded);
			pstmt.setInt(2, konto.getId());
			ResultSet resultsetPW = pstmt.executeQuery();
			
			//dem Kontoobjekt wird das Passwort hinzugefuegt
			while (resultsetPW.next()) {
				erfolg = true;
				konto.setPasswort(resultsetPW.getString("passwort"));
			}
		} catch (SQLException e){
			System.out.println("SQLException cought -> Problem beim Einloggen " + e.toString());
		} finally {
			try {
				con.close();
		} catch (SQLException e) {
			System.err.println("Verbindung geschlossen?" + e.toString());
		}
	}
		return erfolg;
	}
	
	/**
	 * Diese Methode selektiert anhand der Email alle Daten des angemeldeten Kontos aus der DB und speichert sie.
	 * @param email
	 * @return Liste der Daten des angemeldeten Kontos
	 */
	public static Konto getKonto(String email) {
		Konto konto = null;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kundenkonto WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet resultsetKonto = pstmt.executeQuery();
			
			//Konto erstellen
			while (resultsetKonto.next()) {
				konto = new Konto(resultsetKonto.getString("vorname"), resultsetKonto.getString("nachname"), resultsetKonto.getString("email"), 
						resultsetKonto.getString("geburtsdatum"), null, resultsetKonto.getString("iban"), resultsetKonto.getDouble("kontostand"));
				konto.setId(resultsetKonto.getInt("kontoid"));
			}
			
		} catch (SQLException e){
			System.out.println("SQLException cought -> Problem beim Einloggen " + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return konto;
	}
	
	/**
	 * Ermittelt die Kontoid anhand der Email des Kontos.
	 * @param email
	 * @return Kontoid
	 */
	public static int getKontoId(String email) {
		int kontoId = 0;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kundenkonto WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet resultsetId = pstmt.executeQuery();
			
			//Konto erstellen
			while (resultsetId.next()) {
				kontoId = resultsetId.getInt("kontoid");
			}
			
		} catch (SQLException e){
			System.out.println("SQLException cought -> Problem beim Einloggen " + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return kontoId;
	}
	
	/**
	 * Diese Methode prueft bei der Registrierung eines neuen Kontos, ob die eingegebene
	 * Email bereits vergeben ist.
	 * @param email
	 * @return boolean
	 */
	public static boolean emailVorhanden(String email) {
		boolean erfolg = false;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT email FROM kundenkonto WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet resultsetEmail = pstmt.executeQuery();
			
			if (!resultsetEmail.next()) {
				erfolg = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return erfolg;
	}
}
