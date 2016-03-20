package de.javawebstatt.tt.spieler;

import java.io.Serializable;
import java.util.Random;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.schläger.SchlägerI;
import de.javawebstatt.tt.verein.Verein;

public abstract class AbstractSpieler implements SpielerI, Serializable {

	/**
	 * basic version
	 */
	private static final long serialVersionUID = 1L;
	
	private String vorname, nachname;
	private int punkteZahl;
	private SchlägerI schläger;
	private Verein verein;

	private String kürzel;

	private SpielI aktuellesSpiel;

	AbstractSpieler(String vorname, String nachname) {
		super();
		if (vorname == null || nachname == null) {
			throw ModellRuntimeException.NAME_EMPTY;
		}
		this.vorname = vorname;
		this.nachname = nachname;

		this.punkteZahl = new Random().nextInt(9) + 1;
		this.kürzel = vorname.substring(0, 1) + nachname.substring(0,1); 
		// TODO register Kürzel in DB 
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
	public SchlägerI getSchläger() {
		return schläger;
	}

	@Override
	public void setSchläger(SchlägerI schläger) {
		this.schläger = schläger;
	}

	@Override
	public SpielStatus getSpielStatus() {
		if (schläger == null)
			return SpielStatus.KEIN_SCHLÄGER;
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
	public String getKürzel() {
		return kürzel;
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
				+ ", kürzel=" + kürzel + ", verein=" + (verein != null ? verein.getName() : null) + "]";
	}


}
