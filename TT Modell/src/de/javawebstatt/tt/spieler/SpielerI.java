package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.schläger.SchlägerI;
import de.javawebstatt.tt.verein.Verein;

public interface SpielerI {

	Geschlecht getGeschlecht();

	int getPunktezahl();

	void setPunktezahl(int punkteZahl);

	String getVorname();

	String getNachname();
	
	SchlägerI getSchläger(); 
	
	void setSchläger(SchlägerI schläger);
	
	SpielStatus getSpielStatus();

	void setVerein(Verein verein); 

	Verein getVerein();
	
	String getKürzel(); 
	
	void beginneSpiel(SpielI spiel);

	void beendeSpiel(SpielI spiel);
	
	boolean isInSpiel();
	
	SpielI getAktuellesSpiel(); 

}