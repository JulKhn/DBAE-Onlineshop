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
import data.Ware;
import database.ProduktDatabase;

/**
 * MenuServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * Warenkorb aus der Session anfordern und die Produktid sowie die gewaehlte
		 * Menge des Produkts aus der JSP anfordern.
		 */
		HttpSession session = request.getSession();

		ArrayList<Ware> warenKorb = (ArrayList<Ware>) session.getAttribute("warenKorb");
		String produktid = request.getParameter("warenkorbHinzu");
		String menge = request.getParameter("produktMenge");
		session.setAttribute("menge", menge);
		Konto konto = (Konto) session.getAttribute("konto");
		boolean schonDa = false;
		
		
		ArrayList<Produkt> prodListe = ProduktDatabase.produktMenu();
		session.setAttribute("prodListe", prodListe);
		
		/**
		 * Datenbankaufruf nach Produktid des gewaehlten Produkts.
		 * Hier wird das Produkt mit der geforderten Menge im Warenkorb gespeichert.
		 * Falls das Produkt schon hinzugefuegt wurde, kann es nicht direkt nochmal
		 * hinzugefuegt werden. Soll eine groessere Menge gewaehlt werden, so muss
		 * das Produkt nochmal geloescht und neu hinzugefuegt werden.
		 */
		ArrayList<Produkt> produktList = ProduktDatabase.produktMenu();
		
		Ware neueWare = new Ware(produktList.get(Integer.parseInt(produktid) - 2), Integer.parseInt(menge));
		for(Ware w : warenKorb) {
			if(neueWare.getProdukt().getId() == (w.getProdukt().getId())) {
				schonDa = true;
				w.setMenge(w.getMenge() + Integer.parseInt(menge));
			}
		}
		
		if(!schonDa) {
			warenKorb.add(neueWare);
		}
				
		/**
		 * Der aktuelle Warenkorb wird der Session hinzugefügt bzw. aktualisiert
		 */
		session.setAttribute("warenKorb", warenKorb);
		session.setAttribute("leer", false);
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
}
