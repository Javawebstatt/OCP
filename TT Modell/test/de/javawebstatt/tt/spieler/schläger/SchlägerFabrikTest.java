package de.javawebstatt.tt.spieler.schläger;

import static org.junit.Assert.*;

import org.junit.Test;

import de.javawebstatt.tt.spieler.schläger.SchlägerFabrik;
import de.javawebstatt.tt.spieler.schläger.SchlägerI;

public class SchlägerFabrikTest {

	@Test
	public void test() {
		SchlägerI testSchläger = SchlägerFabrik.createSchläger("Irgend", "Etwas");
		assertNotNull(testSchläger.getVorderseite());
		assertNotNull(testSchläger.getRückseite());
	}

}
