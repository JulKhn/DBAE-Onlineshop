package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import data.Produkt;

/**
 * Klasse zur Interaktion mit der Produkt Database
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class ProduktDatabase {
	
	private static Connection con = null;

	/**
	 * Diese Methode selektiert alle existierenden Produkte aus der Datenbank
	 * und sendet sie an das Servlet.
	 * @return Arraylist mit Produkten
	 */
	public static ArrayList<Produkt> produktMenu() {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt ORDER BY produktid");
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge);
				produkte.add(neuesProdukt);
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
		return produkte;
	}
	
	/**
	 * Selektiert alle Produkte einer bestimmten Kategorie aus der Datenbank, um
	 * die Produkte zu filtern.
	 * @param kategorie
	 * @return Arraylist mit Produkten
	 */
	public static ArrayList<Produkt> produktKategorie(String kategorie) {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt WHERE kategorie = ? ORDER BY produktid");
			pstmt.setString(1, kategorie);
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge);
				produkte.add(neuesProdukt);
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
		return produkte;
	}
	
	/**
	 * Sortiert alle Produkte aus der DB aufsteigend nach dem Preis
	 * @return Arraylist mit Produkten
	 */
	public static ArrayList<Produkt> produktPreisAuf() {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt ORDER BY preis");
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge);
				produkte.add(neuesProdukt);
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
		return produkte;
	}
	
	/**
	 * Sortiert alle Produkte aus der DB absteigend nach dem Preis
	 * @return Arraylist mit Produkten
	 */
	public static ArrayList<Produkt> produktPreisAb() {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt ORDER BY preis DESC");
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge);
				produkte.add(neuesProdukt);
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
		return produkte;
	}
	
	/**
	 * Selektiert alle Produkte aus der DB, welche dem eingegebene Namen entsprechen. 
	 * (nicht case sensitiv)
	 * @return Arraylist mit Produkten
	 */
	public static ArrayList<Produkt> produktName(String prodname) {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		System.out.println("prodname DB: " + prodname);
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt WHERE LOWER(name) LIKE LOWER(?)");
			pstmt.setString(1, "%" + prodname + "%");
			System.out.println("Statement: " + pstmt);
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge);
				produkte.add(neuesProdukt);
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
		return produkte;
	}
}
