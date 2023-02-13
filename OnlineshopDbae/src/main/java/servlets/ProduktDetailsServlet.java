package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
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
 * ProduktDetailsServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/ProduktDetailsServlet")
public class ProduktDetailsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Connection con = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 *Von der Menu JSP wird die passende Produktid uebergeben, anhand dieser wird aus 
	 *der Datenbank das Byte-Array des Bildes gezogen. Dieses wird mit Base64 encoded
	 *und dann als Base64 image gespeichert und zum Schluss in der Session gespeichert,
	 *um es in der JSP auszugeben
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		String produktid = request.getParameter("details");
		int prodID = Integer.parseInt(produktid);
		session.setAttribute("details", produktid);
		request.setAttribute("details", produktid);
		ArrayList<Produkt> prodListe = (ArrayList<Produkt>) session.getAttribute("prodListe");
		
		for(int i = 0; i<prodListe.size(); i++) {
			if(prodListe.get(i).getId() == prodID) {
				Produkt aktuellesProdukt = prodListe.get(i);
				session.setAttribute("aktuellesProdukt", aktuellesProdukt);
			}
			
		}
		session.setAttribute("bildID", produktid);
		
		request.getRequestDispatcher("produktSeite.jsp").forward(request, response);
	}

}