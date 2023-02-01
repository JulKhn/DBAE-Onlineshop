package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import data.Konto;

/**
 * LogoutServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		/**
		 * Sobald der eingeloggte Nutzer, unabhaengig auf welcher Seite er sich befindet,
		 * den Logout Button klickt, wird er zur Logout JSP geschickt und die Session wird invalidiert.
		 * Alle Sessiondaten (z.B. Warenkorb) gehen dann verloren.
		 */
		try {
			Konto konto= (Konto) session.getAttribute("konto"); 
			request.setAttribute("vorname", konto.getVorname());
			request.setAttribute("nachname", konto.getNachname());
			session.setAttribute("loginbool", false);
			session.invalidate();
			
		} catch (NullPointerException npe) {
			request.setAttribute("error", "Ups! Die Session ist verloren gegangen.");
		}
		
		request.getRequestDispatcher("logout.jsp").forward(request, response);
	}
}
