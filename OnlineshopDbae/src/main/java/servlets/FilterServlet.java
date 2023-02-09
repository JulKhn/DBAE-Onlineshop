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
		
		HttpSession session = request.getSession();
		ArrayList<Produkt> prodListe = ProduktDatabase.produktMenu();
		
		String name = request.getParameter("produktName");
		if (!name.isEmpty()) {
			prodListe = ProduktDatabase.produktName(name);
			System.out.println("name: " + name);
		}
		
		String kategorie = request.getParameter("kategorie");
		if (!kategorie.isEmpty()) {
			prodListe = ProduktDatabase.produktKategorie(kategorie);
			System.out.println("Kategorie: " + kategorie);
		}
		
		String sortierung = request.getParameter("sortieren");
		if (!sortierung.isEmpty()) {
			if (sortierung.equals("aufsteigend")) {
				System.out.println("Sort 1");
				prodListe = ProduktDatabase.produktPreisAuf();
			} else if (sortierung.equals("absteigend")) {
				System.out.println("Sort 2");
				prodListe = ProduktDatabase.produktPreisAb();
			}
		}
		
		session.setAttribute("prodListe", prodListe);
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
}
