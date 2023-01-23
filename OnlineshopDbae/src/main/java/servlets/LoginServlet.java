package servlets;
import database.DatabaseConnection;
import data.Konto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Hier werden Sessiondaten fuer den Warenkorb ("leer") und 
	 * das Guthaben ("aufladen") initialisiert. Das passiert direkt
	 * am Login, damit waehrend der gesamten Session mit diesen Werten 
	 * gearbeitet werden kann.
	 */
	private boolean loginbool = false;
	Boolean leer = true;
	Boolean aufladen = false;
	private static Connection con = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * E-Mail und Passwort aus der Form anfordern und die oben beschriebenen 
		 * Daten der Session hinzufuegen
		 */
		String email = request.getParameter("email"); 
		String passwort = request.getParameter("passwort");
		String weiterleitung = "index.jsp"; 
		
		HttpSession session = request.getSession(); 
		session.setAttribute("leer", leer);
		session.setAttribute("aufladen", aufladen);
		
		
		try {
			
			//E-Mail mit den E-Mails in der Datenbank abgleichen
			con = DatabaseConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM benutzer WHERE email = '" +  email + "'");
			ResultSet resultsetKonto = pstmt.executeQuery();
			
			//counter um zu pruefen ob wirklich ein Benutzer erstellt wurde
			int counter = 0;
			Konto konto = null;
			
			//benutzerid wird fuer die Abfrage des Passwortes benoetigt und bei der Benutzererstellung initialisiert
			int kontoid = 0;
			
			//Benutzer erstellen
			while (resultsetKonto.next()) {
				counter++;
				konto = new Konto(resultsetKonto.getString("vorname"), resultsetKonto.getString("nachname"), resultsetKonto.getString("email"), 
						resultsetKonto.getString("geburtsdatum"), null, resultsetKonto.getString("iban"), resultsetKonto.getDouble("kontostand"));
				kontoid = resultsetKonto.getInt("benutzerid");
				session.setAttribute("benutzerid", kontoid);
				request.setAttribute("benutzerid", kontoid);
			}
			
			
			if (counter != 0) {
				
				/**
				 * da alle Passwoerter in der Datenbank verschluesselt sind muss auch das angegebene Passwort verschluesselt werden, 
				 * um es mit dem Paswort in der Datenbank abzugleichen.
				 */
				byte[] passwortbyte = passwort.getBytes();
				passwortbyte = Base64.getEncoder().encode(passwortbyte);
				String passwortencoded = new String(passwortbyte);

				
				//Passwort und BenutzerID aus der DB mit Eingabe abgleichen
				PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM passwort WHERE passwort = '" +  passwortencoded + "' AND benutzerid = " + kontoid);
				ResultSet resultsetPW = pstmt2.executeQuery();
				
				//counter fuer die Bestaetigung, dass dem Benutzer das Passwort zugewiesen wurde.
				counter = 0;
				
				//dem Benutzerobjekt wird das Passwort hinzugefuegt
				while (resultsetPW.next()) {
					counter++;
					konto.setPasswort(resultsetPW.getString("passwort"));
				}
				
				/**
				 * Benutzer per expression language begruessen, loginbool fuer die Navbar auf true setzen,
				 * Benutzerobjekt in der Session speichern und den Warenkorb fuer die Session erstellen
				 */
				if (counter != 0) {
					
					request.setAttribute("erfolg", "Willkommen, " + konto.getVorname() + " " + konto.getNachname() + "!");
					session.setAttribute("benutzer", konto);
					session.getAttribute("benutzer");
					
					
					weiterleitung = "menu.jsp";
					loginbool = true;
					session.setAttribute("loginbool", loginbool);
					
				} else {
					request.setAttribute("error", "Die Kombination aus E-Mail/Passwort ist nicht vorhanden."); 
					loginbool = false;
					session.setAttribute("loginbool", loginbool);
				}
			} else {
				request.setAttribute("error", "Konto ist nicht vorhanden, bitte registrieren!"); 
				loginbool = false;
				session.setAttribute("loginbool", loginbool);
			}

		
		} catch (NullPointerException npe) {
			request.setAttribute("error", "Es ist ein Fehler aufgetreten. Haben Sie sich bereits registriert?"); 
		} catch (SQLException e) {
			System.out.println("Fehler: Sind Sie bereits eingeloggt? ");
			e.printStackTrace();
		}
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
