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
	public void testLandesligaS�d() {
		assertEquals(Streifenliga.S�D.getName(), "Streifenliga S�d");
		assertEquals(Bundesliga.BUNDESLIGA, Streifenliga.S�D.getBundesliga());
		assertNotNull(Streifenliga.S�D.getRegionalligen());
		assertEquals(2, Streifenliga.S�D.getRegionalligen().size());
	}

	@Test
	public void testBaw�VaterligenKette() {
		assertNull(Bundesliga.BUNDESLIGA.getVaterLiga());
		assertEquals(Bundesliga.BUNDESLIGA, Landesliga.BAW�.getLandesliga().getBundesliga());
	}

	@Test
	public void testBaw�Kindliste() {
		assertNull(Landesliga.BAW�.getKindLigen());
	}

}
