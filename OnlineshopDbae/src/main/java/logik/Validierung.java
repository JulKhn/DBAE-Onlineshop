package logik;

import java.util.ArrayList;

import data.Konto;

public class Validierung {
	/** Prueft auf einen leeren String. Man haette auch einfach .isEmpty() nehmen koennen :)
	 * @param wert
	 * @return
	 */
	public static boolean istLeer(String wert) {
		boolean leer = false;
		if (wert.equals("")) {
			leer = true;
		}
		return leer;
	}

	/** Pruefe auf Passwortwiederholung. Auch nur "Proof of Concept". 
	 * @param passwort
	 * @param passwortWDH
	 * @return
	 */
	public static boolean istWiederholt(String passwort, String passwortWDH) {
		return passwort.equals(passwortWDH);
	}

	/** Geht die Kundenliste durch und prueft, ob die Kombination aus E-Mail und Passwort bereits existiert. 
	 * Wird fuer die Login-Funktion verwendet. 
	 * @param kundenliste
	 * @param kunde
	 * @return
	 */
	public static boolean kontoExistiertBereits(ArrayList<Konto> kontoliste, Konto konto) {
		boolean erfolg = false;
		String email = konto.getEmail();
		for (Konto neuesKonto : kontoliste) {
			if (email.equals(neuesKonto.getEmail())) {
				erfolg = true;
				System.err.println("[Debug] Kunden wurde bereits registriert.");
			}
		}
		return erfolg;
	}

	/** Prueft, ob bspw. beim Alter eine Zahl eingegeben worden ist.
	 * @param zahl
	 * @return
	 */
	public static boolean istZahl(String zahl) {
		boolean erfolg = false;
		try {
			Integer.parseInt(zahl);
			erfolg = true;
		} catch (NumberFormatException nfe) {
			System.err.println("Alter ist keine Zahl!");
		}
		return erfolg;
	}

}
