package de.javawebstatt.tt.spieler.schläger;

public class SchlägerFabrik {

	public static class SimpleSchläger implements SchlägerI {

		private String marke;
		private String modell;

		public SimpleSchläger(String marke2, String modell2) {
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
			return "Kommt später...";
		}

		@Override
		public String getRückseite() {
			return "Kommt später...";
		}
		
	}
	public static SchlägerI createSchläger(String marke, String modell) {
		SchlägerI schläger = new SimpleSchläger(marke, modell);
		return schläger;
	}

}
