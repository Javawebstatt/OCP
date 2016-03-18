package de.javawebstatt.tt.spieler;

public class SpielerFrau extends AbstractSpieler {

	/**
	 * basic version
	 */
	private static final long serialVersionUID = 1L;

	SpielerFrau(String vorName, String nachName) {
		super(vorName, nachName);
	}

	@Override
	public Geschlecht getGeschlecht() {
		return Geschlecht.MAENNLICH;
	}

	@Override
	public String toString() {
		return "SpielerFrau [" + super.toString() + "]";
	}

}
