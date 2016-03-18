package de.javawebstatt.tt.spieler;

import java.io.Serializable;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
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

	AbstractSpieler(String vorname, String nachname) {
		super();
		if (vorname == null || nachname == null) {
			throw ModellRuntimeException.NAME_EMPTY;
		}
		this.vorname = vorname;
		this.nachname = nachname;
		this.punkteZahl = 0;
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
	public boolean isSpielbereit() {
		return getSpielStatus() == SpielStatus.OK;
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
	public String toString() {
		return "AbstractSpieler [vorname=" + vorname + ", nachname=" + nachname + ", punkteZahl=" + punkteZahl
				+ ", schläger=" + schläger + ", verein=" + (verein != null ? verein.getName() : null) + "]";
	}

}
