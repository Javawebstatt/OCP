package de.javawebstatt.tt.modell;

import de.javawebstatt.tt.modell.api.ISchläger;

public class SchlägerFabrik {

	public static class SimpleSchläger implements ISchläger {

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
	public static ISchläger createSchläger(String marke, String modell) {
		ISchläger schläger = new SimpleSchläger(marke, modell);
		return schläger;
	}

}
