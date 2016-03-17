package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.spieler.schläger.SchlägerI;

public abstract class AbstractSpieler implements SpielerI {

	private String vorname, nachname;
	private int punkteZahl;
	private SchlägerI schläger;

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

}
