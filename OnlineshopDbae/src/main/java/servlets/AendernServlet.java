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
		System.out.println(email);
		System.out.println(kontoid);
		
		/**
		 * Auf Basis des aktuellen Kontostandes des Kontos wird in diesem Block der Kontostand des Kontos geupdatet,
		 * indem dem aktuellen Kontostand der eingegebene Betrag ("geld") addiert wird.
		 */
		String emailAkt = EmailAendernDatabase.getEmail(kontoid);
		 
		//SQL eingabe fuer das Update des Kontostandes des Kontos
		String neueEmail = EmailAendernDatabase.emailAktualisieren(kontoid, email);
			
		//Konto in der Session den neuen Kontostand hinzufuegen
		Konto konto = (Konto) session.getAttribute("konto");
		konto.setEmail(neueEmail);
		session.setAttribute("konto", konto);
		
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
}
