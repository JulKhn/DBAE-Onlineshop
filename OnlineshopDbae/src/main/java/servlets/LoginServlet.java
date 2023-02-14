package servlets;
import database.KontoDatabase;
import database.ProduktDatabase;
import data.Konto;
import data.Produkt;
import data.Ware;
import data.Warenkorb;

import java.io.IOException;
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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
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
		
		Konto konto = KontoDatabase.getKonto(email);
		boolean weiter = false;
		
		if (konto != null) {
			session.setAttribute("konto", konto);
			weiter = true;
		}

		ArrayList<Produkt> prodListe = ProduktDatabase.produktMenu();
		session.setAttribute("prodListe", prodListe);
		
			if (weiter) {
				
				/**
				 * da alle Passwoerter in der Datenbank verschluesselt sind muss auch das angegebene Passwort verschluesselt werden, 
				 * um es mit dem Paswort in der Datenbank abzugleichen.
				 */
				byte[] passwortbyte = passwort.getBytes();
				passwortbyte = Base64.getEncoder().encode(passwortbyte);
				String passwortencoded = new String(passwortbyte);
				
				/**
				 * Konto per expression language begruessen, loginbool fuer die Navbar auf true setzen,
				 * Kontoobjekt in der Session speichern und den Warenkorb fuer die Session erstellen
				 */
				if (KontoDatabase.kontoPasswort(konto, passwortencoded)) {
					request.setAttribute("erfolg", "Willkommen, " + konto.getVorname() + " " + konto.getNachname() + "!");
					session.setAttribute("konto", konto);
					session.getAttribute("konto");
					
					ArrayList<Ware> warenKorb = new ArrayList<Ware>();
					session.setAttribute("warenKorb", warenKorb);
					
					weiterleitung = "menu.jsp";
					loginbool = true;
					Warenkorb warenkorb = new Warenkorb();
					session.setAttribute("Warenkorb", warenkorb);
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

		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
