package de.javawebstatt.tt.verein;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.spieler.SpielerI;

public class Mannschaft implements Serializable{
	/**
	 * basic version 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Verein verein;
	private int num;

	public static final int ANZAHL = 4;
	private SpielerI spieler[] = new SpielerI[ANZAHL];

	public Mannschaft(Verein verein, int num, String name, SpielerI... neueSpieler) {
		this.verein = verein;
		this.num = num;
		this.name = name;
		if (neueSpieler.length != ANZAHL)
			throw ModellRuntimeException.WRONG_NUM_SPIELER;
		else {
			for (int sp = 0; sp < ANZAHL; sp++) {
				spieler[sp] = neueSpieler[sp];
				if (!verein.equals(spieler[sp].getVerein()))
					spieler[sp].setVerein(verein);
			}
		}
	}

	public Verein getVerein() {
		return verein;
	}

	public final String getName() {
		return name;
	}

	public int getNummer() {
		return num;
	}

	public List<SpielerI> getSpieler() {
		return Collections.unmodifiableList(Arrays.asList(spieler)); 
	}

	@Override
	public String toString() {
		String spielerNamen = Arrays.stream(spieler).map(sp -> sp.getVorname()).collect(Collectors.joining("|")); 
		return "Mannschaft [name=" + name + ", verein=" + (verein != null ? verein.getName() : null) + ", num=" + num + ", spieler="
				+ spielerNamen + "]";
	}
}
