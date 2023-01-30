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

	public static ArrayList<Produkt> produktMenu(int id) {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt");
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				System.out.println(resultset);
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
}
