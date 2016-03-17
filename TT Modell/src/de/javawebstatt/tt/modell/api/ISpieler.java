package de.javawebstatt.tt.modell.api;

public interface ISpieler {

	Geschlecht getGeschlecht();

	int getPunktezahl();

	void setPunktezahl(int punkteZahl);

	String getVorname();

	String getNachname();
	
	ISchl�ger getSchl�ger(); 
	
	void setSchl�ger(ISchl�ger schl�ger);
	
	boolean isSpielbereit(); 

	SpielStatus getSpielStatus(); 

}