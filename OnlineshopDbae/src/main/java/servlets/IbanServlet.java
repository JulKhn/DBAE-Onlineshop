package servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Konto;
import database.IbanAendernDatabase;

/**
 * IbanServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/IbanServlet")
public class IbanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		/**
		 *Um die IBAN zu aendern muss der Nutzer eine gueltige IBAN eingeben.
		 *Im Folgenden wird die Eingabe aus der JSP angefordert und ueberprueft.
		 */
		HttpSession session = request.getSession();
		String iban = request.getParameter("iban");
		Konto konto = (Konto) session.getAttribute("konto");
		String error = "";
		boolean erfolg = false;
		
		if (Pattern.matches("^DE[0-9]{20}$", iban)) {
			
			//Sofern alle Eingaben korrekt waren und die neue IBAN einzigartig ist, wird die IBAN des Kontos geaendert.
			erfolg = IbanAendernDatabase.ibanAendern(konto.getId(), iban);
			if (erfolg) {
				error = "Die Iban wurde erfolgreich geändert!";
				konto.setIban(iban);
			} else {
				error = "Fehler bei der Eingabe. Die Iban muss immer einzigartig sein!";
			}
			
		} else {
			error += "Die IBAN entspricht nicht den Vorgaben! Die IBAN muss mit 'DE' beginnen. Danach müssen 20 Ziffern folgen.";
		}
		
		session.setAttribute("konto", konto);
		request.setAttribute("error", error);
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
}
