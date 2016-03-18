package de.javawebstatt.umgebung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Verein;

public class VereineUndSpieler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private Deque<Verein> vereinsDeque = new ConcurrentLinkedDeque<>();
	private Deque<SpielerI> spielerDeque = new ConcurrentLinkedDeque<>();

	public Deque<Verein> getVereinsListe() {
		return vereinsDeque;
	}
	
	public void addVerein(Verein verein) {
		vereinsDeque.add(verein);
	}

	public void addVerein(Verein... vereine) {
		for(Verein v: vereine){
			addVerein(v);
		}
	}

	public Deque<SpielerI> getSpielerListe() {
		return spielerDeque;
	}

	public void addSpieler(SpielerI spieler) {
		spielerDeque.add(spieler);
	}

	public void addVerein(SpielerI... spieler) {
		for(SpielerI s: spieler){
			addSpieler(s);
		}
	}

	@Override
	public String toString() {
		return "VereineUndSpieler [\nvereinsListe=" + vereinsDeque + ", \nspielerListe=" + spielerDeque + "]";
	}

}
