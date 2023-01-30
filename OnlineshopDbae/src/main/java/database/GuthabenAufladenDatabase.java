package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuthabenAufladenDatabase {

private static Connection con = null;
	
	public static double getKontostand(int Kontoid) {
		double kontostand = 0.0;
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT kontostand FROM kundenkonto WHERE kontoid = ?");
			pstmt.setInt(1, Kontoid);
			ResultSet resultsetKontostand = pstmt.executeQuery();
			
			//aktuellen Kontostand aus der DB ziehen und dem Double zuweisen
			while (resultsetKontostand.next()) {
				kontostand = resultsetKontostand.getDouble("kontostand");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontostand;
	}
	
	public static double kontostandAktualisieren(int Kontoid, double geld) {
		double neuerKontostand = 0.0;
		try {
			con = DatabaseConnection.getConnection();
			double kontostand = getKontostand(Kontoid);
			
			//neuerkontostand setzt sich aus dem aktuellen und dem eingegebenen Geld zusammen
			neuerKontostand = kontostand + geld;
			System.out.println("Stand: " + neuerKontostand);
			
			//SQL eingabe fuer das Update des Kontostandes des Kontos
			PreparedStatement pstmt = con.prepareStatement("UPDATE kundenkonto SET kontostand = ? WHERE kontoid = ?");
			pstmt.setDouble(1, neuerKontostand);
			pstmt.setInt(2, Kontoid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return neuerKontostand;
	}
	
	public static int getKontoId(String passwortencoded) {
		int Kontoid = 0;
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT kontoid FROM passwort WHERE passwort = ?");
			pstmt.setString(1, passwortencoded);
			ResultSet resultsetPw = pstmt.executeQuery();
			
			//Kontoid initialisieren
			while (resultsetPw.next()) {
				Kontoid = resultsetPw.getInt("kontoid");
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
		
		return Kontoid;
	}
	
	public static String getIban(int Kontoid) {
		String iban = null;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kundenkonto WHERE kontoid = ?");
			pstmt.setInt(1, Kontoid);
			ResultSet resultsetIban = pstmt.executeQuery();
			
			while (resultsetIban.next()) {
				iban = resultsetIban.getString("iban");
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
		
		return iban;
		
	}
}
