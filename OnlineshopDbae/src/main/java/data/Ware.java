package data;

/**
 * Ware Objekt
 * @author Julian Kuhn / Tim Fricke
 *
 */
public class Ware {

	private Produkt produkt;
	private int menge;
	
	/**
	 * Ware Konstruktor
	 * @param produkt
	 * @param menge
	 */
	public Ware(Produkt produkt, int menge) {
		this.produkt = produkt;
		this.menge = menge;
	}
	
	public Produkt getProdukt() {
		return produkt;
	}
	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
}
