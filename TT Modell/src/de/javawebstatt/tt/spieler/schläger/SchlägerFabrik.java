package de.javawebstatt.tt.spieler.schl�ger;

public class Schl�gerFabrik {

	public static class SimpleSchl�ger implements Schl�gerI {

		private String marke;
		private String modell;

		public SimpleSchl�ger(String marke2, String modell2) {
			this.marke = marke2;
			this.modell = modell2; 
		}

		@Override
		public String getMarke() {
			return marke;
		}

		@Override
		public String getModell() {
			return modell;
		}

		@Override
		public String getVorderseite() {
			return "Kommt sp�ter...";
		}

		@Override
		public String getR�ckseite() {
			return "Kommt sp�ter...";
		}
		
	}
	public static Schl�gerI createSchl�ger(String marke, String modell) {
		Schl�gerI schl�ger = new SimpleSchl�ger(marke, modell);
		return schl�ger;
	}

}
