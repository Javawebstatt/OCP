package de.javawebstatt.tt.spieler;

public class SpielerMann extends AbstractSpieler {

	SpielerMann(String vorname, String nachname) {
		super(vorname, nachname);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.WEIBLICH;
	}

}
