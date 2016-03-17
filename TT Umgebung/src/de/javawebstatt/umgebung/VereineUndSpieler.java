package de.javawebstatt.umgebung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.javawebstatt.tt.verein.Verein;

public class VereineUndSpieler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Verein> vereinsListe = new ArrayList<>();

	public List<Verein> getVereinsListe() {
		return vereinsListe;
	}

	public void addVerein(Verein verein) {
		vereinsListe.add(verein);
	}

	@Override
	public String toString() {
		return "VereineUndSpieler [vereinsListe=" + vereinsListe + "]";
	}

}
