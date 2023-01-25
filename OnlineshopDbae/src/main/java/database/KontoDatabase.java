package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import data.Konto;

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
			pstmt.setString(4, konto.getIban());
			pstmt.setString(5, konto.getGeburtsdatum());
			pstmt.setDouble(6, konto.getKontostand());
			pstmt.executeUpdate();
			
			//Passwort des Kontoss verschluesselt in eigener Tabelle speichern
			PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO passwort (kontoid, passwort) VALUES (" +
					"(SELECT kontoid FROM kundenkonto WHERE email = ?), " +
					"? )"); 		//passwort
			
			//Encoden des Passworts, sodass es verschl�sselt in der Datenbank steht. Base64 als standard-Verschl�sselung
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
	
	public static Konto kontoLogin(String email) {
		Konto konto = null;
		
		try {
			
			//E-Mail mit den E-Mails in der Datenbank ablgleichen
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kundenkonto WHERE email = ?");
			pstmt.setString(1, email);
			ResultSet resultsetKonto = pstmt.executeQuery();

			//Konto erstellen
			while (resultsetKonto.next()) {
				konto = new Konto(resultsetKonto.getString("vorname"), resultsetKonto.getString("nachname"), resultsetKonto.getString("email"), 
						resultsetKonto.getString("geburtsdatum"), null, resultsetKonto.getString("iban"), resultsetKonto.getDouble("kontostand"));
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
						resultsetKonto.getString("geburtsdatum"), null,
						resultsetKonto.getString("iban"), resultsetKonto.getDouble("kontostand"));
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
}