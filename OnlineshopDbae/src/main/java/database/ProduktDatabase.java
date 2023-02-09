package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import data.Produkt;

public class ProduktDatabase {
	
	private static Connection con = null;

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
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, farbe.getBytes());
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
	
	public static String produktBild(int produktid) {
		String base64Image = "";
		
		try {
			con = DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT bild FROM produkt WHERE produktid = ?");
            pstmt.setInt(1, produktid);
            ResultSet resultset = pstmt.executeQuery();
                    
            while (resultset.next()) {
            base64Image = Base64.getEncoder().encodeToString(resultset.getBytes("bild"));
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
		
		return base64Image;
	}
	
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
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, farbe.getBytes());
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
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, farbe.getBytes());
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
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, farbe.getBytes());
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
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, farbe.getBytes());
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
