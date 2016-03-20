package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.schläger.SchlägerI;
import de.javawebstatt.tt.verein.Verein;

public interface SpielerI {

	String getKürzel();  // DB, key mit generator

	String getVorname(); // DB

	String getNachname(); // DB 
	
	Geschlecht getGeschlecht(); // DB, character feld 'M', 'W'

	int getPunktezahl(); // DB int

	void setPunktezahl(int punkteZahl);

	SpielStatus getSpielStatus();

	void setVerein(Verein verein); 

	Verein getVerein(); 
	
	SchlägerI getSchläger(); 
	
	void setSchläger(SchlägerI schläger);
	
	void beginneSpiel(SpielI spiel);

	void beendeSpiel(SpielI spiel);
	
	boolean isInSpiel();
	
	SpielI getAktuellesSpiel(); 

}