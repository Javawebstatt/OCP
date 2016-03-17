package de.javawebstatt.tt.modell.api;

public enum SpielStatus {
	
	OK("Alles gut !"), KEIN_SCHLÄGER("Habe keinen Schläger !"); 
	
	private String erklärung;
	private SpielStatus(String erklärung){
		this.erklärung = erklärung; 
	}
	public String getErklaerung() {
		return erklärung; 
	}
	
}
