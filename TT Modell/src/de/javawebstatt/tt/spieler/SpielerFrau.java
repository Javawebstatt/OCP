package de.javawebstatt.tt.spieler;

public class SpielerFrau extends AbstractSpieler {

	SpielerFrau(String vorName, String nachName) {
		super(vorName, nachName);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.MAENNLICH;
	}

}
