package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Konto;
import database.GuthabenAufladenDatabase;

/**
 * AufladenServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/AufladenServlet")
public class AufladenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * Geldsumme welche aufgeladen werden soll aus der Form anfordern und die Kontoid des aktuellen Kontos aus der Session anfordern.
		 * Kontostand wird auf Basis der Daten aus der Datenbank aktualisiert
		 */
		HttpSession session = request.getSession();
		String geld = request.getParameter("geld");
		int kontoid = (Integer) session.getAttribute("kontoAuf");
		
		/**
		 * Auf Basis des aktuellen Kontostandes des Kontos wird in diesem Block der Kontostand des Kontos geupdatet,
		 * indem dem aktuellen Kontostand der eingegebene Betrag ("geld") addiert wird.
		 */
		//SQL eingabe fuer das Update des Kontostandes des Kontos
		double neuerkontostand = GuthabenAufladenDatabase.kontostandAktualisieren(kontoid, Double.parseDouble(geld));
			
		//Konto in der Session den neuen Kontostand hinzufuegen
		Konto konto = (Konto) session.getAttribute("konto");
		konto.setKontostand(neuerkontostand);
		session.setAttribute("konto", konto);
		
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
}
