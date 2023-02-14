package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Klasse zum Aendern der IBAN
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class IbanAendernDatabase {

	private static Connection con = null;
	
	
	/**
	 * Hier wird die IBAN eines Kontos mithilfe der Kontoid geaendert.
	 * @param kontoid
	 * @param iban
	 * @return Boolean
	 */
	public static boolean ibanAendern(int kontoid, String iban) {
		boolean erfolg = false;
		
		try {
			con = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("UPDATE kundenkonto SET iban = ? WHERE kontoid = ?");
			pstmt.setString(1, iban);
			pstmt.setInt(2, kontoid);
			pstmt.executeUpdate();
			
			erfolg = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Verbindung geschlossen?" + e.toString());
			}
		}
		return erfolg;
	}
}
