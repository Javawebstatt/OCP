package de.javawebstatt.tt.spieler;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;

public class SpielerBuilder {

	private static final String[] NACHNAMEN = { "Müller", "Schmidt", "Meier", "Fischer", "Schulze", "Schneider",
			"Weber", "Bauer", "Wagner", "Becker", "Hoffmann", "Koch", "Richter", "Klein", "Wolf", "Schröder", "Neumann",
			"Schwarz", "Zimmermann", "Krüger", "Hartmann", "Werner", "Krause" };

	private String vorname;
	private String nachname;
	private int punktezahl;
	private Geschlecht geschlecht;
	private boolean generiereNachnamen = false;

	public SpielerBuilder() {

	}

	public boolean isGeneriereNachnamen() {
		return generiereNachnamen;
	}

	public void setGeneriereNachnamen(boolean generiereNachnamen) {
		this.generiereNachnamen = generiereNachnamen;
	}

	public SpielerBuilder setzeVorname(String vorname) {
		this.vorname = vorname;
		return this;
	}

	public SpielerBuilder setzeNachname(String nachname) {
		this.nachname = nachname;
		return this;
	}

	public SpielerBuilder generateNachname() {
		if (vorname == null)
			throw ModellRuntimeException.NAME_EMPTY;
		int quersumme = vorname.chars().sum();
		nachname = NACHNAMEN[quersumme % NACHNAMEN.length];
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

	public SpielerI build() {
		SpielerI spieler = null;

		if (geschlecht == null)
			throw ModellRuntimeException.NO_GESCHLECHT;
		if (vorname == null && nachname == null)
			throw ModellRuntimeException.NAME_EMPTY;

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
