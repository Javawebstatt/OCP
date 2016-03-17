package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.spieler.schl�ger.Schl�gerI;

public interface SpielerI {

	Geschlecht getGeschlecht();

	int getPunktezahl();

	void setPunktezahl(int punkteZahl);

	String getVorname();

	String getNachname();
	
	Schl�gerI getSchl�ger(); 
	
	void setSchl�ger(Schl�gerI schl�ger);
	
	boolean isSpielbereit(); 

	SpielStatus getSpielStatus(); 

}