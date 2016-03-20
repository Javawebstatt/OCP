package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.schl�ger.Schl�gerI;
import de.javawebstatt.tt.verein.Verein;

public interface SpielerI {

	String getK�rzel();  // DB, key mit generator

	String getVorname(); // DB

	String getNachname(); // DB 
	
	Geschlecht getGeschlecht(); // DB, character feld 'M', 'W'

	int getPunktezahl(); // DB int

	void setPunktezahl(int punkteZahl);

	SpielStatus getSpielStatus();

	void setVerein(Verein verein); 

	Verein getVerein(); 
	
	Schl�gerI getSchl�ger(); 
	
	void setSchl�ger(Schl�gerI schl�ger);
	
	void beginneSpiel(SpielI spiel);

	void beendeSpiel(SpielI spiel);
	
	boolean isInSpiel();
	
	SpielI getAktuellesSpiel(); 

}