package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Konto;
import data.Produkt;
import database.KaufVerlaufDatabase;

/**
 * BestellVerlaufServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/BestellVerlaufServlet")
public class BestellVerlaufServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/**
		 * Hier wird auf Basis des angemeldeten Kontos ein DB-Aufruf gestartet, in dem der
		 * Bestellverlauf eines Kontos abgerufen wird. Sollte das Konto noch keine
		 * Bestellungen aufgegeben haben, wird ein Boolean in die JSP uebergeben und ein
		 * Text ausgegeben.
		 * Da hierbei das Servlet als erstes aufgerufen und dann zur JSP weitergeleitet wird, 
		 * verwenden wir die doGet Methode.
		 */
		Konto konto = (Konto) session.getAttribute("konto");
		int kontoid = konto.getId();
		
		ArrayList<Produkt> bestellungen = KaufVerlaufDatabase.kaufVerlauf(kontoid);
		
		boolean inhalt = true;
		if (bestellungen.isEmpty()) {
			inhalt = false;
		}
		
		session.setAttribute("bestelltListe", bestellungen);
		session.setAttribute("verlaufInhalt", inhalt);
		
		request.getRequestDispatcher("kaufverlauf.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
}
