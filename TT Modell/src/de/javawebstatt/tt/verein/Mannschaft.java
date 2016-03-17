package de.javawebstatt.tt.verein;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.javawebstatt.tt.spieler.SpielerI;

public class Mannschaft {
	private String name; 
	private Verein verein; 
	
	public static final int ANZAHL = 6;
	private SpielerI spieler[] = new SpielerI[ANZAHL];
	
	public Mannschaft(Verein verein, String name) {
		this.verein = verein;
		this.name = name; 
	} 
	
	public Verein getVerein(){
		return verein; 
	}
	
	public final String getName() {
		return name;
	}

	public List<SpielerI> getSpieler(){
		return Collections.unmodifiableList(Arrays.asList(spieler)); 
	}
}
