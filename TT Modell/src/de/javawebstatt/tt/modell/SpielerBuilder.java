package de.javawebstatt.tt.modell;

import de.javawebstatt.tt.exceptions.ModellException;
import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.modell.api.Geschlecht;
import de.javawebstatt.tt.modell.api.ISpieler;

public class SpielerBuilder {

	private String vorname;
	private String nachname;
	private int punktezahl;
	private Geschlecht geschlecht;

	public SpielerBuilder() {

	}

	public SpielerBuilder setzeVorname(String vorname) {
		this.vorname = vorname;
		return this;
	}

	public SpielerBuilder setzeNachname(String nachname) {
		this.nachname = nachname;
		return this;
	}

	public SpielerBuilder setzePunktezahl(int punktezahl) {
		this.punktezahl = punktezahl;
		return this;
	}

	public SpielerBuilder setzeGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
		return this;
	}

	public ISpieler build() throws ModellException {
		ISpieler spieler = null;
		
		if (geschlecht == null)
			throw ModellRuntimeException.NO_GESCHLECHT;

		switch (geschlecht) {
		case MAENNLICH:
			spieler = new SpielerMann(vorname, nachname);
			break;
		case WEIBLICH:
			spieler = new SpielerFrau(vorname, nachname);
			break;
		}
		
		spieler.setPunktezahl(punktezahl);
		
		return spieler;
	}
}
