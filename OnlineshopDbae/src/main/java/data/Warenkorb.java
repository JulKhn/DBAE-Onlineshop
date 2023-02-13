package data;

import java.util.HashMap;

public class Warenkorb {

		HashMap<Produkt, Integer> warenkorb;

		public Warenkorb() {
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
		
		@Override
		public String toString() {
			return "Warenkorb [warenkorb=" + warenkorb + "]";
		}

		public int getWarenkorbmenge(int menge) {
			return menge;
		}
}
