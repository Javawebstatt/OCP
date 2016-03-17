package de.javawebstatt.tt.modell.api;

public interface ISpieler {

	Geschlecht getGeschlecht();

	int getPunktezahl();

	void setPunktezahl(int punkteZahl);

	String getVorname();

	String getNachname();
	
	ISchläger getSchläger(); 
	
	void setSchläger(ISchläger schläger);
	
	boolean isSpielbereit(); 

	SpielStatus getSpielStatus(); 

}