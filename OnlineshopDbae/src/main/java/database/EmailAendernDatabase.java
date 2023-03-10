package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import data.Konto;

/**
 * Klasse, um mit der E-Mail in der Datenbank zu arbeiten
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class EmailAendernDatabase {

	private static Connection con = null;
	
	/**
	 * Findet die zu einem bestimmten Konto zugehoerige Email heraus
	 * @param Kontoid
	 * @return email
	 */
	public static String getEmail(int Kontoid) {
		String email = "";
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT email FROM kundenkonto WHERE kontoid = ?");
			pstmt.setInt(1, Kontoid);
			ResultSet resultsetEmail = pstmt.executeQuery();
			
			//aktuelle Email aus der DB ziehen und dem String zuweisen
			while (resultsetEmail.next()) {
				email = resultsetEmail.getString("email");
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
		return email;
	}
	
	/**
	 * Aktualisiert die Email eines Kontos, wenn der Nutzer seine Email aendern moechte
	 * @param kontoid
	 * @param email
	 * @return boolean
	 */
	public static boolean emailAktualisieren(int kontoid, String email) {
		boolean erfolg = false;
		try {
			con = DatabaseConnection.getConnection();
			
			//SQL eingabe fuer das Update des Kontostandes des Kontos
			PreparedStatement pstmt = con.prepareStatement("UPDATE kundenkonto SET email = ? WHERE kontoid = ?");
			pstmt.setString(1, email);
			pstmt.setInt(2, kontoid);
			pstmt.executeUpdate();
			
			erfolg = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return erfolg;
	}
	
	/**
	 * Prueft, ob ein eingegebenes Passwort mit dem Passwort aus der DB uebereinstimmt
	 * @param kontoid
	 * @param passwortencoded
	 * @return
	 */
	public static boolean kontoPasswort(int kontoid, String passwortencoded) {
		boolean erfolg = false;
		String pw = "";
		
		try {
			con = DatabaseConnection.getConnection();
			
			//Passwort und KontoID aus der DB mit Eingabe abgleichen
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM passwort WHERE kontoid = ? AND passwort = ?");
			pstmt.setInt(1, kontoid);
			pstmt.setString(2, passwortencoded);
			ResultSet resultsetPW = pstmt.executeQuery();
			
			while (resultsetPW.next()) {
				pw = resultsetPW.getString("passwort");
			}
			
			if (pw.equals(passwortencoded)) {
				erfolg = true;
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
}
