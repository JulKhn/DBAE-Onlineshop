package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Produkt;
import database.ProduktDatabase;

/**
 * FilterServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 *In dieser Methode werden die Datenbankabfragen zu den Filteranfragen getaetigt.
		 */
		HttpSession session = request.getSession();
		ArrayList<Produkt> prodListe = ProduktDatabase.produktMenu();
		
		/**
		 *Sofern nach dem Namen gefiltert werden soll wird der Name aus der JSP gezogen und
		 *in der DB nicht case sensitiv nach dem Namen gesucht.
		 */
		String name = request.getParameter("produktName");
		if (!name.isEmpty()) {
			prodListe = ProduktDatabase.produktName(name);
		}
		
		/**
		 *Fuer die drei existierenden Kategorien wird die gewaehlte Kategorie
		 *aus der JSP gezogen und in der Datenbank selektiert.
		 */
		String kategorie = request.getParameter("kategorie");
		if (!kategorie.isEmpty()) {
			prodListe = ProduktDatabase.produktKategorie(kategorie);
		}
		
		/**
		 *Um die aufgefuehrten Produkte zu sortieren wird je nach ausgewaehlter Sortierweise
		 *in der JSP eine Methode zum Sortieren der DB Ausgabe aufgerufen.
		 */
		String sortierung = request.getParameter("sortieren");
		if (!sortierung.isEmpty()) {
			if (sortierung.equals("aufsteigend")) {
				prodListe = ProduktDatabase.produktPreisAuf();
			} else if (sortierung.equals("absteigend")) {
				prodListe = ProduktDatabase.produktPreisAb();
			}
		}
		
		session.setAttribute("prodListe", prodListe);
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
}
