package de.javawebstatt.tt.modell;

import de.javawebstatt.tt.modell.api.Geschlecht;

public class SpielerMann extends AbstractSpieler {

	SpielerMann(String vorname, String nachname) {
		super(vorname, nachname);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.WEIBLICH;
	}

}
