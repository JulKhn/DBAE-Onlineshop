package data;

/**
 * Produkt Objekt
 * @author Julian Kuhn, Tim Fricke
 *
 */
public class Produkt {

	private int id;
	private String name;
	private String groesse;
	private double preis;
	private String farbe;
	private int menge;
	private String datum;
	

	/**
	 * Drei verschiedene Konstruktoren fuer das Produkt
	 * @param id
	 * @param name
	 * @param groesse
	 * @param preis
	 * @param farbe
	 * @param menge
	 * @param datum
	 */
	public Produkt(int id, String name, String groesse, double preis, String farbe, int menge) {
		super();
		this.id = id;
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
	}
	
	public Produkt(String name, String groesse, double preis, String farbe, int menge) {
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
	}
	
	public Produkt(String name, String groesse, double preis, String farbe, int menge, String datum, int id) {
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.datum = datum;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getGroesse() {
		return groesse;
	}

	public double getPreis() {
		return preis;
	}

	public String getFarbe() {
		return farbe;
	}

	public int getMenge() {
		return menge;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}
	
}
