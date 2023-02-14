package servlets;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.EmailAendernDatabase;
import database.GuthabenAufladenDatabase;

/**
 * EmailServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * Um die Email zu aendern muss das Passwort des Kontos eingegeben werden.
		 * Hier wird dann ueberprueft, ob es mit dem Konto uebereinstimmt.
		 */
		String weiterleitung = "email.jsp";
		HttpSession session = request.getSession();
		byte[] passwort = request.getParameter("passwort").getBytes();
		String error = "";
		
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
			} else {
				error += "Das eigegebene Passwort stimmt nicht mit dem Ihres Kontos überein.";
			}
			
		request.setAttribute("error", error);
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
}
