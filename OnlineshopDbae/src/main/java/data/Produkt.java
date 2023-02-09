package data;

public class Produkt {

	private int id;
	private String name;
	private String groesse;
	private double preis;
	private String farbe;
	private int menge;
	private byte[] bild;
	private String datum;
	

	public Produkt(int id, String name, String groesse, double preis, String farbe, int menge, byte[] bild) {
		super();
		this.id = id;
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.bild = bild;
	}
	
	public Produkt(String name, String groesse, double preis, String farbe, int menge, byte[] bild) {
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.bild = bild;
	}
	
	public Produkt(String name, String groesse, double preis, String farbe, int menge, String datum) {
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.datum = datum;
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

	public byte[] getBild() {
		return bild;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
}
