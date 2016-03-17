package de.javawebstatt.tt.verein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.javawebstatt.tt.spieler.SpielerI;

public class Verein implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Stadt stadt;
	private String name;
	private Set<SpielerI> mitglieder;
	private List<Mannschaft> mannschaften;

	public Verein(Stadt stadt, String name) {
		this.stadt = stadt;
		this.name = name;
		mitglieder = new HashSet<>();
		mannschaften = new ArrayList<>();
	}

	public final Stadt getStadt() {
		return stadt;
	}

	public final String getName() {
		return name;
	}

	public void addMitglied(SpielerI spieler) {
		mitglieder.add(spieler);
	}

	public int gründeMannschaft() {
		int num = mannschaften.size();
		String newName = name + num + ". Mannschaft";
		Mannschaft newMannschaft = new Mannschaft(this, newName);
		mannschaften.add(newMannschaft);
		return num; 
	}

	@Override
	public String toString() {
		return "Verein [stadt=" + stadt + ", name=" + name + ", mitglieder=" + mitglieder + ", mannschaften="
				+ mannschaften + "]";
	}
}
