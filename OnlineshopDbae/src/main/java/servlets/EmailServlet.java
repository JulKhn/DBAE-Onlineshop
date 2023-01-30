package servlets;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.EmailAendernDatabase;
import database.GuthabenAufladenDatabase;

/**
 * AendernServlet
 * @author Julian Kuhn / Tim Fricke
 */
public class EmailServlet {

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
		String weiterleitung = "email.jsp";
		HttpSession session = request.getSession();
		byte[] passwort = request.getParameter("passwort").getBytes();
		
		System.out.println("TEst1234");
		
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
			
			//ist das Passwort des Kontos gleich mit dem eingegebenen, so wird der Benutzer auf die aendern JSP weitergeleitet
			if(EmailAendernDatabase.kontoPasswort(kontoid, passwortencoded)) {
				session.setAttribute("aendern", true);
				weiterleitung = "aendern.jsp";
			}
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
