package de.javawebstatt.tt.spieler;

public class SpielerMann extends AbstractSpieler {

	/**
	 * basic version
	 */
	private static final long serialVersionUID = 1L;

	SpielerMann(String vorname, String nachname) {
		super(vorname, nachname);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.WEIBLICH;
	}

	@Override
	public String toString() {
		return "SpielerMann [" + super.toString() + "]";
	}

}
