package de.javawebstatt.umgebung;

import static org.junit.Assert.*;

import org.junit.Test;

import de.javawebstatt.tt.verein.Stadt;
import de.javawebstatt.tt.verein.Verein;

public class VereineUndSpielerTest {

	@Test
	public void testVereineInListe() {
		VereineUndSpieler vus = new VereineUndSpieler(); 
		Verein neuerVerein = new Verein(Stadt.H, "Sch�tzenverein");
		vus.addVerein(neuerVerein);

		assertEquals(1, vus.getVereinsListe().size());
		assertEquals(neuerVerein, vus.getVereinsListe().get(0));
	}

	@Test
	public void testSpeichernUndReload() {
		VereineUndSpieler orig = new VereineUndSpieler(); 
		Verein neuerVerein = new Verein(Stadt.H, "Sch�tzenverein");
		orig.addVerein(neuerVerein);
		VereineUndSpielerPersist.speichern(orig, "basic-test-01", "test");
		
		VereineUndSpieler reload = VereineUndSpielerPersist.laden("basic-test-01", "test");
		assertEquals(orig.toString(), reload.toString());
	}

}
