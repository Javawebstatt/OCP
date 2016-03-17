package de.javawebstatt.tt.modell;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.modell.api.Geschlecht;
import de.javawebstatt.tt.modell.api.ISchl�ger;
import de.javawebstatt.tt.modell.api.ISpieler;
import de.javawebstatt.tt.modell.api.SpielStatus;

public abstract class AbstractSpieler implements ISpieler {

	private String vorname, nachname;
	private int punkteZahl;
	private ISchl�ger schl�ger;

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
	public ISchl�ger getSchl�ger() {
		return schl�ger;
	}

	@Override
	public void setSchl�ger(ISchl�ger schl�ger) {
		this.schl�ger = schl�ger;
	}

	@Override
	public boolean isSpielbereit() {
		return getSpielStatus() == SpielStatus.OK;
	}

	@Override
	public SpielStatus getSpielStatus() {
		if (schl�ger == null)
			return SpielStatus.KEIN_SCHL�GER;
		else
			return SpielStatus.OK;
	}

}
