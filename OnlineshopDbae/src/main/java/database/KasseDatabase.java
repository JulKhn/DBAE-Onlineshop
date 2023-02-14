package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import data.Ware;
import data.Warenkorb;

/**
 * Klasse zum Kauf von Produkten
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class KasseDatabase {
	
private static Connection con = null;
	
	/**
	 * Bei dieser Methode wird die Menge eines bestimmten Produktes aktualisiert. Sollte ein Produkt in 
	 * bestimmter Menge gekauft werden, so wird die vorraetige Menge des Produktes um die gekaufte Menge
	 * reduziert.
	 * @param warenkorb
	 * @param warenKorb
	 */
	public static void mengeAktualisieren(Warenkorb warenkorb, ArrayList<Ware> warenKorb) {
		
		try {
			con = DatabaseConnection.getConnection();
			
			for (Ware w : warenKorb) {
				PreparedStatement pstmtMengeUpdate = con.prepareStatement("UPDATE produkt SET menge = ? WHERE produktid = ?");
				pstmtMengeUpdate.setInt(1, w.getProdukt().getMenge());
				pstmtMengeUpdate.setInt(2, w.getProdukt().getId());
				pstmtMengeUpdate.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("[ERROR] Beim aktualisieren der Menge");
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
	 * Sollte ein Produkt nicht mehr vorhanden sein (Menge = 0) wird es mithilfe dieser Methode aus der
	 * Datenbank geloescht.
	 * @param warenkorb
	 * @param warenKorb
	 */
	public static void produktEntfernen(Warenkorb warenkorb, ArrayList<Ware> warenKorb) {
		
		try {
			con = DatabaseConnection.getConnection();
					
			for (Ware w : warenKorb) {
				w.getProdukt().setMenge(w.getProdukt().getMenge() - w.getMenge());
				PreparedStatement pstmtProdEntf = con.prepareStatement("DELETE FROM produkt WHERE produktid = ?");
				pstmtProdEntf.setInt(1, w.getProdukt().getId());
				pstmtProdEntf.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.err.println("[ERROR] Beim Entfernen des Produkts");
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
