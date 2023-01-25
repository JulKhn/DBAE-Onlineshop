package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Produkt;

public class ProduktDatabase {
	
	private static Connection con = null;

	public static ArrayList<Produkt> produktMenu(int id) {
		
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		
		try {
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM produkt WHERE NOT benutzerid = ?");
			pstmt.setInt(1, id);
			ResultSet resultset = pstmt.executeQuery();
			
			while (resultset.next()) {
				int prodID = resultset.getInt(1);
				System.out.println(prodID);
				String name = resultset.getString(3);
				String groesse = resultset.getString(4);
				double preis = resultset.getDouble(8);
				String farbe = resultset.getString(5);
				int menge = resultset.getInt(6);
				byte[] bild = resultset.getBytes(9);
				String beschreibung = resultset.getString(7);
				
				Produkt neuesProdukt = new Produkt(prodID, name, groesse, preis, farbe, menge, bild, beschreibung);
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
