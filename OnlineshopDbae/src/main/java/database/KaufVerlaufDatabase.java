package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.Produkt;

/**
 * Klasse fuer den Bestellverlauf
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class KaufVerlaufDatabase {
	
	private static Connection con = null;
	
	/**
	 * In dieser Methode werden einfach alle Produkte, die ein bestimmtes Konto erworben hat
	 * in eine Liste gespeichert und an das Servlet weitergegeben.
	 * @param kontoid
	 * @return Arraylist mit bestellten Produkten
	 */
	public static ArrayList<Produkt> kaufVerlauf(int kontoid) {
		
		ArrayList<Produkt> bestelltListe = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM bestellverlauf WHERE kontoid = ? ORDER BY datum");
			pstmt.setInt(1, kontoid);
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(6);
				String datum = resultset.getString(7);
				int id = resultset.getInt(8);
				
				Produkt neuesProdukt = new Produkt(name, groesse, preis, farbe, menge, datum, id);
				bestelltListe.add(neuesProdukt);
			}
			
		} catch (SQLException e){
			System.out.println("SQLException cought -> " + e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return bestelltListe;
	}
	
	/**
	 * Wenn ein Nutzer die Produkte in seinem Warenkorb kauft, werden sie hiermit automatisch der 
	 * Datenbank hinzugefuegt und einer bestimmten Zeit zugewiesen.
	 * @param kontoid
	 * @param datum
	 * @param menge
	 * @param name
	 * @param produktid
	 */
	public static void bestelltHinzu(int kontoid, Date datum, int menge, String name, int produktid) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO bestellverlauf (kontoid, menge, datum, name, produktid) VALUES (?, ?, ?, ?, ?)");
			pstmt.setInt(1, kontoid);
			pstmt.setInt(2, menge);
			pstmt.setString(3, dateFormat.format(datum));
			pstmt.setString(4, name);
			pstmt.setInt(5, produktid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
	}
	
	/**
	 * Um das Insert Into Statement aus bestelltHinzu kleiner zu halten, werden hier alle restlichen Werte
	 * des gekauften Produkts in die DB bestellverlauf kopiert.
	 * @param produktid
	 * @param datum
	 * @param name
	 */
	public static void produktdatenHinzu(int produktid, Date datum, String name) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE bestellverlauf SET (preis, farbe, groesse)"
					+ " = (SELECT preis, farbe, groesse FROM produkt WHERE produktid = ?) WHERE datum = ? AND name = ?");
			pstmt.setInt(1, produktid);
			pstmt.setString(2, dateFormat.format(datum));
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
	}
}
