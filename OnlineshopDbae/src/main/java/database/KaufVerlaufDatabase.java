package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Produkt;

public class KaufVerlaufDatabase {
	
	private static Connection con = null;
	
	public static ArrayList<Produkt> KaufVerlauf() {
		
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

}
