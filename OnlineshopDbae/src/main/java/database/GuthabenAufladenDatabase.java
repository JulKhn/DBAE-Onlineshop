package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Klasse, bei der mit dem Guthaben der Konten gearbeitet werden kann
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class GuthabenAufladenDatabase {

private static Connection con = null;
	
	/**
	 * Ermittelt den aktuellen Kontostand eines bestimmten Kontos
	 * @param Kontoid
	 * @return aktueller Kontostand
	 */
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
	
	/**
	 * Diese Methode aktualisiert den Kontostand eines bestimmten Kontos, indem ein vom Nutzer eingegebener Betrag mit dem 
	 * aktuellen Kontostand zusammengerechnet und ein Update in der DB ausgefuehrt wird.
	 * @param kontoid
	 * @param geld
	 * @return neuer Kontostand
	 */
	public static double kontostandAktualisieren(int kontoid, double geld) {
		double neuerKontostand = 0.0;
		try {
			con = DatabaseConnection.getConnection();
			double kontostand = getKontostand(kontoid);
			
			//neuerkontostand setzt sich aus dem aktuellen und dem eingegebenen Geld zusammen
			neuerKontostand = kontostand + geld;
			
			//SQL eingabe fuer das Update des Kontostandes des Kontos
			PreparedStatement pstmt = con.prepareStatement("UPDATE kundenkonto SET kontostand = ? WHERE kontoid = ?");
			pstmt.setDouble(1, neuerKontostand);
			pstmt.setInt(2, kontoid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return neuerKontostand;
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn der Nutzer einen Kauf taetigt. Hier wird der Kontostand
	 * des Nutzers um den entsprechenden Wert reduziert.
	 * @param Kontoid
	 * @param geld
	 * @return neuer Kontostand
	 */
	public static double kontostandAbziehen(int Kontoid, double geld) {
		double neuerKontostand = 0.0;
		try {
			con = DatabaseConnection.getConnection();
			
			//neuerkontostand setzt sich aus dem aktuellen und dem eingegebenen Geld zusammen
			neuerKontostand = geld;
			
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
	
	/**
	 * Bei dieser Methode soll die Kontoid des Nutzers herausgefunden werden, indem das Passwort eingegeben wird.
	 * @param passwortencoded
	 * @return kontoid
	 */
	public static int getKontoId(String passwortencoded) {
		int kontoid = 0;
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT kontoid FROM passwort WHERE passwort = ?");
			pstmt.setString(1, passwortencoded);
			ResultSet resultsetPw = pstmt.executeQuery();
			
			//Kontoid initialisieren
			while (resultsetPw.next()) {
				kontoid = resultsetPw.getInt("kontoid");
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
		
		return kontoid;
	}
	
	/**
	 * Hier soll die Iban des Kontos mithilfe der Kontoid herausgefunden werden.
	 * @param kontoid
	 * @return Iban
	 */
	public static String getIban(int kontoid) {
		String iban = null;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM kundenkonto WHERE kontoid = ?");
			pstmt.setInt(1, kontoid);
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
