package logik;

import java.util.ArrayList;

import data.Konto;

public class Validierung {
	/** Prüft auf einen leeren String. Man hätte auch einfach .isEmpty() nehmen können :)
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

	/** Prüfe auf Passwortwiederholung. Auch nur "Proof of Concept". 
	 * @param passwort
	 * @param passwortWDH
	 * @return
	 */
	public static boolean istWiederholt(String passwort, String passwortWDH) {
		return passwort.equals(passwortWDH);
	}

	/** Geht die Kundenliste durch und prüft, ob die Kombination aus E-Mail und Passwort bereits existiert. 
	 * Wird für die Login-Funktion verwendet. 
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

	/** Prüft, ob bspw. beim Alter eine Zahl eingegeben worden ist.
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

	/** Sucht nach der höchsten ID in der Kontoliste. Wird verwendet, um 
	 * anschließend die nächste ID zu inkrementieren (für den nächsten Kontoeintrag). 
	 * @param kontoliste
	 * @return
	 */
	/*public static int getHoechsteID(ArrayList<Konto> kontoliste) {
		int counter = 0;
		for (Konto konto : kontoliste) {
			counter = konto.getKontoID();
		}
		return counter;
	}*/

}
