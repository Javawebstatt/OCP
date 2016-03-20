package de.javawebstatt.tt.spieler;

import java.io.Serializable;
import java.util.Random;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.schl�ger.Schl�gerI;
import de.javawebstatt.tt.verein.Verein;

public abstract class AbstractSpieler implements SpielerI, Serializable {

	/**
	 * basic version
	 */
	private static final long serialVersionUID = 1L;
	
	private String vorname, nachname;
	private int punkteZahl;
	private Schl�gerI schl�ger;
	private Verein verein;

	private String k�rzel;

	private SpielI aktuellesSpiel;

	AbstractSpieler(String vorname, String nachname) {
		super();
		if (vorname == null || nachname == null) {
			throw ModellRuntimeException.NAME_EMPTY;
		}
		this.vorname = vorname;
		this.nachname = nachname;

		this.punkteZahl = new Random().nextInt(9) + 1;
		this.k�rzel = vorname.substring(0, 1) + nachname.substring(0,1); 
		// TODO register K�rzel in DB 
	}

	@Override
	public abstract Geschlecht getGeschlecht();

	@Override
	public int getPunktezahl() {
		return punkteZahl;
	}

	@Override
	public void setPunktezahl(int punkteZahl) {
		this.punkteZahl = punkteZahl;
	}

	@Override
	public String getVorname() {
		return vorname;
	}

	@Override
	public String getNachname() {
		return nachname;
	}

	@Override
	public Schl�gerI getSchl�ger() {
		return schl�ger;
	}

	@Override
	public void setSchl�ger(Schl�gerI schl�ger) {
		this.schl�ger = schl�ger;
	}

	@Override
	public SpielStatus getSpielStatus() {
		if (schl�ger == null)
			return SpielStatus.KEIN_SCHL�GER;
		else
			return SpielStatus.OK;
	}

	@Override
	public void setVerein(Verein verein) {
		this.verein = verein;
	}

	public Verein getVerein() {
		return verein;
	}

	@Override
	public String getK�rzel() {
		return k�rzel;
	}

	@Override
	public void beginneSpiel(SpielI spiel) {
		if(aktuellesSpiel != null)
			throw ModellRuntimeException.PLAYER_ALREADY_PLAYS;
		
		aktuellesSpiel = spiel; 
	}

	@Override
	public void beendeSpiel(SpielI spiel) {
		if(aktuellesSpiel != spiel)
			throw ModellRuntimeException.PLAYER_PLAYS_OTHER_GAME;
		
		aktuellesSpiel = null; 
	}

	@Override
	public boolean isInSpiel() {
		return aktuellesSpiel != null;
	}

	@Override
	public SpielI getAktuellesSpiel() {
		return aktuellesSpiel;
	}

	@Override
	public String toString() {
		return "AbstractSpieler [vorname=" + vorname + ", nachname=" + nachname + ", punkteZahl=" + punkteZahl
				+ ", k�rzel=" + k�rzel + ", verein=" + (verein != null ? verein.getName() : null) + "]";
	}


}
