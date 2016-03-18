package de.javawebstatt.tt.verein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
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

	public final Set<SpielerI> getMitglieder() {
		return mitglieder;
	}

	public final List<Mannschaft> getMannschaften() {
		return mannschaften;
	}

	public void addMitglied(SpielerI spieler) {
		mitglieder.add(spieler);
		spieler.setVerein(this);
	}

	public void addMitglieder(SpielerI... spieler) {
		for(SpielerI sp : spieler){
			if(sp.getVerein() == null) {
				sp.setVerein(this);
				mitglieder.add(sp); 
			} else if(sp.getVerein() == this) {
				// alles o.k. 
			} else {
				throw new ModellRuntimeException("Mitglied " + spieler + " kann nicht hinzugefügt werden, Verein bereits belegt !"); 
			}
		}
	}

	public Mannschaft gründeMannschaft(SpielerI... spieler) {
		addMitglieder(spieler);
		int num = mannschaften.size() + 1;
		String newName = num + ".M. von " + name;
		Mannschaft newMannschaft = new Mannschaft(this, num, newName, spieler);
		mannschaften.add(newMannschaft);
		return newMannschaft;
	}

	@Override
	public String toString() {
		return "Verein [stadt=" + stadt + ", name=" + name + ", mitglieder=" + mitglieder + ", mannschaften="
				+ mannschaften + "]";
	}

}
