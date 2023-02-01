package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Ware;

/**
 * WarenkorbServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/WarenkorbServlet")
public class WarenkorbServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * Hier wird der Warenkorb verwaltet.
		 * Der Benutzer soll die moeglichkeit haben Produkte wieder aus dem Warenkorb zu löschen, 
		 * indem das Produkt mit der zugehoerigen Produktid durch einen Button aus dem Warenkorb 
		 * geloescht wird.
		 */
		HttpSession session = request.getSession(); 
		ArrayList<Ware> warenKorb = (ArrayList<Ware>) session.getAttribute("warenKorb");
		String produktid = request.getParameter("produktloeschen");
		int prodID = Integer.parseInt(produktid);
	
		for (Ware w : warenKorb) {
			if(w.getProdukt().getId() == prodID) {
				warenKorb.remove(w);
				if (warenKorb.size() == 0) {
					session.setAttribute("leer", true);
				}
				break;
			}
			
		}
		
		session.setAttribute("warenKorb", warenKorb);

		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}
}
