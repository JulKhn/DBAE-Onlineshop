package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.Produkt;

public class KaufVerlaufDatabase {
	
	private static Connection con = null;
	
	public static ArrayList<Produkt> kaufVerlauf(int kontoid) {
		
		ArrayList<Produkt> bestelltListe = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM bestellverlauf WHERE kontoid = ?");
			pstmt.setInt(1, kontoid);
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				String name = resultset.getString(2);
				String groesse = resultset.getString(5);
				double preis = resultset.getDouble(3);
				String farbe = resultset.getString(4);
				int menge = resultset.getInt(6);
				String datum = resultset.getString(7);
				
				Produkt neuesProdukt = new Produkt(name, groesse, preis, farbe, menge, datum);
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
	
	public static void bestelltHinzu(int kontoid, Date datum, int menge, String name) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO bestellverlauf (kontoid, menge, datum, name) VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, kontoid);
			pstmt.setInt(2, menge);
			pstmt.setString(3, dateFormat.format(datum));
			pstmt.setString(4, name);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
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
		} 
		
	}

}
