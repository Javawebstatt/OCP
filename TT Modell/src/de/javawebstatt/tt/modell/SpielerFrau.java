package de.javawebstatt.tt.modell;

import de.javawebstatt.tt.modell.api.Geschlecht;

public class SpielerFrau extends AbstractSpieler {

	SpielerFrau(String vorName, String nachName) {
		super(vorName, nachName);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.MAENNLICH;
	}

}
