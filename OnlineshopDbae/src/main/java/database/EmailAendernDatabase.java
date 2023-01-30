package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.Konto;

public class EmailAendernDatabase {

	private static Connection con = null;
	
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
		}
		
		return email;
	}
	
	public static String emailAktualisieren(int Kontoid, String email) {
		String neueEmail = "";
		try {
			con = DatabaseConnection.getConnection();
			
			//SQL eingabe fuer das Update des Kontostandes des Kontos
			PreparedStatement pstmt = con.prepareStatement("UPDATE kundenkonto SET kontostand = ? WHERE kontoid = ?");
			pstmt.setString(1, email);
			pstmt.setInt(2, Kontoid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return neueEmail;
	}
	
	public static boolean kontoPasswort(int kontoid, String passwortencoded) {
		boolean erfolg = false;
		
		try {
			con = DatabaseConnection.getConnection();
			
			//Passwort und KontoID aus der DB mit Eingabe abgleichen
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM passwort WHERE passwort = ? AND kontoid = ?");
			pstmt.setString(1, passwortencoded);
			pstmt.setInt(2, kontoid);
			ResultSet resultsetPW = pstmt.executeQuery();
			
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
