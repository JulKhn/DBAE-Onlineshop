package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.GuthabenAufladenDatabase;

/**
 * GuthabenServlet
 * @author Julian Kuhn / Tim Fricke
 */
public class GuthabenServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * Iban und Passwort aus der Form ziehen
		 */
		String weiterleitung = "guthaben.jsp";
		HttpSession session = request.getSession();
		String iban = request.getParameter("iban");
		byte[] passwort = request.getParameter("passwort").getBytes();
		
		/**
		 * Im try block wird ueberprueft, ob iban und passwort mit denen des akutellen Kontos uebereinstimmen
		 * um eine Weiterleitung auf die aufladen JSP zu erlauben.
		 */
		//Passwort verschluesseln, damit es mit dem Passwort in der Datenbank uebereinstimmt
		passwort = Base64.getEncoder().encode(passwort);
		String passwortencoded = new String(passwort);
			
		//kontoid mit dem Passwort herausfinden
		int kontoid = GuthabenAufladenDatabase.getKontoId(passwortencoded);
			
		session.setAttribute("kontoAuf", kontoid);
		request.setAttribute("kontoAuf", kontoid);
		
			//IBAN des Kontos herausfinden und mit der eingegebenen IBAN abgleichen
			String iban2 = GuthabenAufladenDatabase.getIban(kontoid);
			
			//ist die IBAN gleich der eingegebenen IBAN wird der Konto auf die aufladen JSP weitergeleitet
			if(iban2.equals(iban)) {
				session.setAttribute("aufladen", true);
				weiterleitung = "aufladen.jsp";
			}
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
