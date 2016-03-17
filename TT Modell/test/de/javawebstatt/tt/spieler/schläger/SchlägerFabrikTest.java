package de.javawebstatt.tt.spieler.schl�ger;

import static org.junit.Assert.*;

import org.junit.Test;

import de.javawebstatt.tt.spieler.schl�ger.Schl�gerFabrik;
import de.javawebstatt.tt.spieler.schl�ger.Schl�gerI;

public class Schl�gerFabrikTest {

	@Test
	public void test() {
		Schl�gerI testSchl�ger = Schl�gerFabrik.createSchl�ger("Irgend", "Etwas");
		assertNotNull(testSchl�ger.getVorderseite());
		assertNotNull(testSchl�ger.getR�ckseite());
	}

}
