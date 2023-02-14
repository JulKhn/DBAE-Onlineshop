package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.GuthabenAufladenDatabase;

/**
 * GuthabenServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/GuthabenServlet")
public class GuthabenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * Um das Guthaben des Kontos aufzuladen muss der Nutzer Iban und Passwort des Kontos eingeben.
		 * Diese Eingaben werden im Folgenden aus der JSP gezogen.
		 */
		String weiterleitung = "guthaben.jsp";
		HttpSession session = request.getSession();
		String iban = request.getParameter("iban");
		byte[] passwort = request.getParameter("passwort").getBytes();
		String error = "";
		
		
		//Passwort verschluesseln, damit es mit dem Passwort in der Datenbank uebereinstimmt
		passwort = Base64.getEncoder().encode(passwort);
		String passwortencoded = new String(passwort);
			
		//kontoid mit dem Passwort herausfinden
		int kontoid = GuthabenAufladenDatabase.getKontoId(passwortencoded);
		
		//wird eine Kontoid gefunden, so faehrt das Programm fort
		boolean weiter = true;
		if (kontoid == 0) {
			weiter = false;
		}
			
		if (weiter) {
			session.setAttribute("kontoAuf", kontoid);
			request.setAttribute("kontoAuf", kontoid);
			
			//IBAN des Kontos herausfinden und mit der eingegebenen IBAN abgleichen
			String iban2 = GuthabenAufladenDatabase.getIban(kontoid);
				
			//ist die IBAN das Kontos gleich der eingegebenen IBAN wird das Konto auf die aufladen JSP weitergeleitet
			if(iban2.equals(iban)) {
				session.setAttribute("aufladen", true);
				weiterleitung = "aufladen.jsp";
			} else {
				error = "Die eingegebene IBAN stimmt nicht mit der IBAN Ihres Kontos überein";
			}
		}
		
		request.setAttribute("error", error);
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
