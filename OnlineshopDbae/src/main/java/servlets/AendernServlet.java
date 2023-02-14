package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Konto;
import database.EmailAendernDatabase;
import database.GuthabenAufladenDatabase;

/**
 * AendernServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/AendernServlet")
public class AendernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Email welche verändert werden soll aus der Form anfordern und die Kontoid des aktuellen Kontos aus der Session anfordern.
		 * Email wird auf Basis der Daten aus der Datenbank aktualisiert
		 */
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int kontoid = (Integer) session.getAttribute("kontoAuf");
		boolean erfolg = false;
		String error = "";
		
		/**
		 * Nach einer Ueberpruefung, ob die Anfrage, die Email zu aendern, gueltig ist,
		 * wird die neue Email in der Session gespeichert und dem Konto hinzugefuegt. Sollte
		 * Die Email bereits vorhanden sein, so wird ein Fehler ausgegeben.
		 */
		
		//SQL eingabe fuer das Update der Email des Kontos
		erfolg = EmailAendernDatabase.emailAktualisieren(kontoid, email);
		System.out.println("Erfolg? " + erfolg);
		if (erfolg) {
			error = "Die E-Mail wurde erfolgreich geändert!";
			String neueEmail = EmailAendernDatabase.getEmail(kontoid);
			
			//Konto in der Session die neue Email hinzufuegen
			Konto konto = (Konto) session.getAttribute("konto");
			konto.setEmail(neueEmail);
			
			session.setAttribute("konto", konto);
			
		} else {
			error = "Fehler bei der Eingabe. Die E-Mail muss immer einzigartig sein!";
		}
		
		request.setAttribute("error", error);
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
}
