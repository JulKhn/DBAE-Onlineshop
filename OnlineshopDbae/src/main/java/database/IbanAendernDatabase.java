package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IbanAendernDatabase {

	private static Connection con = null;
	
	
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
		} 
		
		return erfolg;
	}
}
