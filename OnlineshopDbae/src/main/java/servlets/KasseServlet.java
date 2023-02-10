package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Konto;
import data.Produkt;
import data.Ware;
import data.Warenkorb;
import database.GuthabenAufladenDatabase;
import database.KasseDatabase;
import database.KaufVerlaufDatabase;
import database.ProduktDatabase;

/**
 * KasseServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/KasseServlet")
public class KasseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		/**
		 * Warenkorb und Konto aus der Session holen
		 */
		Warenkorb warenkorb = (Warenkorb) session.getAttribute("Warenkorb");
		ArrayList<Ware> warenKorb = (ArrayList<Ware>) session.getAttribute("warenKorb");
		Konto konto = (Konto) session.getAttribute("konto");
		
		String summe = request.getParameter("kaufAbschluss");
		double kontostand = konto.getKontostand();
		double neuerKstand = 0.0;
		String weiterleitung = "kasse.jsp";
		
		//Ueberpruefung ob genug Geld vorhanden ist
		boolean weiter = false;
		if (kontostand - Double.parseDouble(summe) >= 0) {
			neuerKstand = kontostand - Double.parseDouble(summe);
			konto.setKontostand(neuerKstand);
			weiterleitung = "warenkorb.jsp";
			weiter = true;
		} else {
				request.setAttribute("error", "Ihr Kontostand ist zu gering um die Produkte zu kaufen. Bitte Geld aufladen!");
				request.setAttribute("fehler", true);
		}
			
		session.setAttribute("konto", konto);
		
		int kontoid = konto.getId();
		
		//Die Menge der Produkte die gekauft werden, werden aktualisiert. Falls Menge = 0 wird das Produkt aus der DB entfernt
		if (weiter) {
			for (Ware w : warenKorb) {
				w.getProdukt().setMenge(w.getProdukt().getMenge() - w.getMenge());
				System.out.println("Menge abzug:" + w.getMenge());
				System.out.println("Name produkt:" + w.getProdukt().getName());
				Date datum = new Date();
				KaufVerlaufDatabase.bestelltHinzu(kontoid, datum, w.getMenge(), w.getProdukt().getName());
				KaufVerlaufDatabase.produktdatenHinzu(w.getProdukt().getId(), datum, w.getProdukt().getName());
				if(w.getProdukt().getMenge() > 0) {
					KasseDatabase.mengeAktualisieren(warenkorb, warenKorb);
				} else {
					KasseDatabase.produktEntfernen(warenkorb, warenKorb);
				}
		}
			
			//neuen Kontostand des Kunden in die DB schreiben
			GuthabenAufladenDatabase.kontostandAbziehen(kontoid, neuerKstand);
			
			warenkorb.getWarenkorb().clear();
			warenKorb.clear();
			
			ArrayList<Produkt> bestellungen = KaufVerlaufDatabase.kaufVerlauf(kontoid);
			
			session.setAttribute("bestelltListe", bestellungen);
			session.setAttribute("leer", true);
			
			request.setAttribute("erfolg", "Ihre Produkte wurden erfolgreich gekauft!");
		}
		
		ArrayList<Produkt> prodListe = ProduktDatabase.produktMenu();
		session.setAttribute("prodListe", prodListe);
		
		session.setAttribute("warenKorb", warenKorb);
		session.setAttribute("warenkorbListe", warenkorb.getWarenkorb());
		request.setAttribute("warenkorbListe", warenkorb.getWarenkorb());
		
		request.getRequestDispatcher(weiterleitung).forward(request, response);
	}
	
}
