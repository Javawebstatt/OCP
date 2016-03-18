package de.javawebstatt.umgebung;

import static org.junit.Assert.*;

import org.junit.Test;

import de.javawebstatt.tt.verein.Stadt;
import de.javawebstatt.tt.verein.Verein;

public class VereineUndSpielerTest {

	@Test
	public void testVereineInListe() {
		VereineUndSpieler vus = new VereineUndSpieler(); 
		Verein neuerVerein = new Verein(Stadt.H, "Schützenverein");
		vus.addVerein(neuerVerein);

		assertEquals(1, vus.getVereinsListe().size());
		assertEquals(neuerVerein, vus.getVereinsListe().poll());
	}

	@Test
	public void testSpeichernUndReload() {
		VereineUndSpieler orig = new VereineUndSpieler(); 
		Verein neuerVerein = new Verein(Stadt.H, "Schützenverein");
		orig.addVerein(neuerVerein);
		VereineUndSpielerPersist.speichern(orig, "testSpeichernUndReload", "test");
		
		VereineUndSpieler reload = VereineUndSpielerPersist.laden("testSpeichernUndReload", "test");
		assertEquals(orig.toString(), reload.toString());
	}

}
