package servlets;

import database.DatabaseConnection;
import database.KontoDatabase;
import logik.Validierung;
import data.Konto;
import java.util.Date;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * RegistierungsServlet
 * @author Julian Kuhn / Tim Fricke
 */
@WebServlet("/RegistrierungsServlet")
public class RegistrierungsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 *Die Daten aus dem Formular nach Pruefung in die DB schreiben. 
	 *Benutzer erstellen und in die Session einfuegen
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. Schritt: Daten aus dem Formular holen
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String geburtsdatum = request.getParameter("geburtsdatum");
		String email = request.getParameter("email");
		String passwort = request.getParameter("passwort");
		String passwortWDH = request.getParameter("passwortWDH");
		String iban = request.getParameter("iban");
		String error = "";
		String weiterleitung = "registrierung.jsp"; 
		

		// 2. Schritt: Daten validieren
		if (!Validierung.istLeer(vorname)) {
			request.setAttribute("inputVorname", vorname);
		}
		if (!Validierung.istLeer(nachname)) {
			request.setAttribute("inputNachname", nachname);
		}
		if (!Validierung.istLeer(email)) {
			request.setAttribute("inputEmail", email);
		}
		if (!Validierung.istLeer(iban)) {
			request.setAttribute("inputIban", iban);
		}
		if (!Validierung.istLeer(geburtsdatum)) {
			request.setAttribute("inputGeburtsdatum"
					+ "<", geburtsdatum);
		}
		if (!Validierung.istWiederholt(passwort, passwortWDH)) {
			error += "Passwoerter stimmen nicht Ueberein. ";
		}
		

		//ReGex zur Prüfung der Eingegebenen Namen und Emails
		if(Pattern.matches("([A-Z]{1,1}[a-z]+[ -]?([A-Z]{1,1}[a-z]+)?)", vorname) && Pattern.matches("([A-Z]{1,1}[a-z]+[ -]?([A-Z]{1,1}[a-z]+)?)", nachname)) {
			if (Pattern.matches("(([a-z.+-]{1,63})@[a-z.+-]{5,254})", email)) {
				if (Pattern.matches("^DE[0-9]{20}$", iban)) {
			//	if (Validierung.istZahl(alter)) {
					if (!Validierung.istLeer(vorname) && !Validierung.istLeer(nachname) && !Validierung.istLeer(email)
							&& !Validierung.istLeer(passwort) && !Validierung.istLeer(passwortWDH) //&& !Validierung.istLeer(geburtsdatum)
							&& Validierung.istWiederholt(passwort, passwortWDH) && !Validierung.istLeer(iban)) {

						System.out.println("[DEBUG] Alle Felder wurden erfolgreich ausgefuellt.");

						HttpSession session = request.getSession();
						Konto konto = new Konto(vorname, nachname, email, geburtsdatum, passwort, iban, 0);
						
						if (KontoDatabase.kontoEinfuegen(konto)) {
							session.setAttribute("konto", konto);
						}

						ArrayList<Konto> kontoliste = new ArrayList<Konto>();
						try {
							
							kontoliste = (ArrayList<Konto>) session.getAttribute("kontoliste");
							
							if (!Validierung.kontoExistiertBereits(kontoliste, konto)) {
								session.setAttribute("benutzer", konto);
								kontoliste.add(konto);
								session.setAttribute("benutzerliste", kontoliste);
								session.setAttribute("erfolg", "neuer Benutzer wurde erfolgreich registriert.");
								weiterleitung = "index.jsp"; 
							} else {
								request.setAttribute("inputGeburtsdatum", geburtsdatum);
								error += "Kunde existiert bereits!";
							}
						} catch (NullPointerException npe) {
							session.setAttribute("benutzer", konto);
							kontoliste = new ArrayList<Konto>();
							kontoliste.add(konto);
							session.setAttribute("benutzerliste", kontoliste);
							session.setAttribute("erfolg", "neuer Benutzer wurde erfolgreich registriert.");
							weiterleitung = "index.jsp"; 
						}

					} else {
						error += "Mindestens ein verpflichtendes Feld wurde nicht ausgefuellt! ";
					}
				} else {
					error += "Die IBAN entspricht nicht den Vorgaben! Die IBAN muss mit 'DE' beginnen. Danach müssen 20 Ziffern folgen.";
				}
			}  else {
				error += "Die E-Mail entspricht nicht den Vorgaben! Ihre E-Mail muss aus zwei Teilen, welche mit einem @ getrennt werden bestehen und darf nur aus Kleinbuchstaben, Punkten, Plus und Minus bestehen. Maximale Länge sind 254 Zeichen.";
			}
		} else {
			error += "Der eingegebene Name entspricht nicht den Vorgaben! Der erste Buchstabe muss ein Großbuchstabe sein und es sind nur Leerzeichen und Bindestriche erlaubt.";
		}
		
		
		
		
		// 3. Weiterleiten
		request.setAttribute("error", error);
		request.getRequestDispatcher(weiterleitung).forward(request, response);
		
	}
}