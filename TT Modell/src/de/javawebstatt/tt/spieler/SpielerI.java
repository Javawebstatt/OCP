package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.spieler.schläger.SchlägerI;

public interface SpielerI {

	Geschlecht getGeschlecht();

	int getPunktezahl();

	void setPunktezahl(int punkteZahl);

	String getVorname();

	String getNachname();
	
	SchlägerI getSchläger(); 
	
	void setSchläger(SchlägerI schläger);
	
	boolean isSpielbereit(); 

	SpielStatus getSpielStatus(); 

}