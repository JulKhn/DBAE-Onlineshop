package data;

public class Produkt {

	private int id;
	private String name;
	private String groesse;
	private double preis;
	private String farbe;
	private int menge;
	private byte[] bild;
	private String beschreibung;
	

	public Produkt(int id, String name, String groesse, double preis, String farbe, int menge, byte[] bild,
			String beschreibung) {
		super();
		this.id = id;
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.bild = bild;
		this.beschreibung = beschreibung;
	}
	
	public Produkt(String name, String groesse, double preis, String farbe, int menge, byte[] bild,
			String beschreibung) {
		this.name = name;
		this.groesse = groesse;
		this.preis = preis;
		this.farbe = farbe;
		this.menge = menge;
		this.bild = bild;
		this.beschreibung = beschreibung;
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

	public byte[] getBild() {
		return bild;
	}

	public String getBeschreibung() {
		return beschreibung;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	/*
	private void produktHinzu(String name) {
		  v.addElement(name);
	}

    private void produktEntf(String name) {
		  v.removeElement(name);
	}

    public void setProdukt(String name) {
		  produkt = name;
    }
		    
    public void setAbsenden(String s) {
		  absenden = s;
    }

    public String[] getProdukte() {
		  String[] s = new String[v.size()];
		  v.copyInto(s);
		  return s;
    }
	
	public void processRequest(HttpServletRequest request) {
		  // null value for submit - user hit enter instead of clicking on 
		  // "add" or "remove"
		  if (absenden == null) 
		      produktHinzu(produkt);

		  if (absenden.equals("add"))
			  produktHinzu(produkt);
		  else if (absenden.equals("remove")) 
			  produktEntf(produkt);
		  
		  // reset at the end of the request
		  reset();
	}

		    // reset
	private void reset() {
		  absenden= null;
		  produkt = null;
	}*/
}
