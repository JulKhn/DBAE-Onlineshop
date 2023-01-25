package data;

import java.util.HashMap;

public class Warenkorb {

		//ArrayList<Produkt> warenkorb;
		HashMap<Produkt, Integer> warenkorb;

		public Warenkorb() {
			//warenkorb = new ArrayList<Produkt>();
			warenkorb = new HashMap<Produkt, Integer>();
		}

		public HashMap<Produkt, Integer> getWarenkorb() {
			return warenkorb;
		}
		
		public int warenkorbLen() {
			return warenkorb.size();
		}

		public void setWarenkorb(HashMap<Produkt, Integer> warenkorb) {
			this.warenkorb = warenkorb;
		}

		
		/*public void ausgabe(ArrayList<Produkt> warenkorb) {
			System.out.println("Print Arraylist using for loop");
	        for(int i=0; i < warenkorb.size(); i++){
	            System.out.println( warenkorb.get(i) );
	        }
		}*/
		
		@Override
		public String toString() {
			return "Warenkorb [warenkorb=" + warenkorb + "]";
		}

		public int getWarenkorbmenge(int menge) {
			return menge;
		}
}
