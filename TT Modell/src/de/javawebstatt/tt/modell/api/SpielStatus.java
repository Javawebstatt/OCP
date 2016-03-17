package de.javawebstatt.tt.modell.api;

public enum SpielStatus {
	
	OK("Alles gut !"), KEIN_SCHL�GER("Habe keinen Schl�ger !"); 
	
	private String erkl�rung;
	private SpielStatus(String erkl�rung){
		this.erkl�rung = erkl�rung; 
	}
	public String getErklaerung() {
		return erkl�rung; 
	}
	
}
