package de.javawebstatt.tt.liga;

import static org.junit.Assert.*;

import org.junit.Test;

public class BundesligaTest {

	@Test
	public void testAndPrintBundesliga() {
		assertEquals(Bundesliga.BUNDESLIGA.getName(), "Bundesliga");
		assertNotNull(Bundesliga.BUNDESLIGA.getLandesligen());
		assertEquals(3, Bundesliga.BUNDESLIGA.getLandesligen().size());
		System.out.println(Bundesliga.BUNDESLIGA);
	}

	@Test
	public void testLandesligaNord() {
		assertEquals(Streifenliga.NORD.getName(), "Streifenliga Nord");
		assertEquals(Bundesliga.BUNDESLIGA, Streifenliga.NORD.getBundesliga());
		assertNotNull(Streifenliga.NORD.getRegionalligen());
		assertEquals(3, Streifenliga.NORD.getRegionalligen().size());
	}

	@Test
	public void testLandesligaMitte() {
		assertEquals(Streifenliga.MITTE.getName(), "Streifenliga Mitte");
		assertEquals(Bundesliga.BUNDESLIGA, Streifenliga.MITTE.getBundesliga());
		assertNotNull(Streifenliga.MITTE.getRegionalligen());
		assertEquals(4, Streifenliga.MITTE.getRegionalligen().size());
	}

	@Test
	public void testLandesligaSüd() {
		assertEquals(Streifenliga.SÜD.getName(), "Streifenliga Süd");
		assertEquals(Bundesliga.BUNDESLIGA, Streifenliga.SÜD.getBundesliga());
		assertNotNull(Streifenliga.SÜD.getRegionalligen());
		assertEquals(2, Streifenliga.SÜD.getRegionalligen().size());
	}

	@Test
	public void testBawüVaterligenKette() {
		assertNull(Bundesliga.BUNDESLIGA.getVaterLiga());
		assertEquals(Bundesliga.BUNDESLIGA, Landesliga.BAWÜ.getLandesliga().getBundesliga());
	}

	@Test
	public void testBawüKindliste() {
		assertNull(Landesliga.BAWÜ.getKindLigen());
	}

}
