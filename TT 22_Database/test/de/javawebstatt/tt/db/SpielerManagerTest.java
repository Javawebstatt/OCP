package de.javawebstatt.tt.db;

import static de.javawebstatt.tt.db.SpielerManager.getInstance;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;

public class SpielerManagerTest {

	private String defaultVorname;
	private String defaultNachname;

	@Before
	public void before() {
		defaultVorname = "Klaus";
		defaultNachname = "M�ller";
	}

	@Test
	public void zugriffsInstanzKorrekt() throws Exception {
		SpielerManager sm = getInstance();
		assertNotNull("sm nicht null !", sm);
	}

	@Test
	public void l�scheAlleDates�tzeZuK�rzel() throws Exception {
		int count = getInstance().ermittleAnzahlK�rzel(defaultVorname, defaultNachname);
		assertTrue("Positive Anzahl Datens�tze", count >= 0);
		int delete = getInstance().deleteAlleK�rzel(defaultVorname, defaultNachname);
		assertEquals(count, delete);
		count = getInstance().ermittleAnzahlK�rzel(defaultVorname, defaultNachname);
		assertEquals(0, count);
	}

	@Test
	public void ermittleKorrektesK�rzel() throws Exception {
		String k�rzel = getInstance().createNeuesK�rzel(defaultVorname, defaultNachname);
		assertEquals(4, k�rzel.length());
		assertEquals("KM", k�rzel.substring(0, 2));
		assertNotNull(Integer.valueOf(k�rzel.substring(2, 4)));

	}

	@Test
	public void legeNeuenDatensatzAnUndCheckeWerte() throws Exception {
		String vorname2 = "Norbert";
		String nachname2 = "Neu";
		int punkte2 = (int) (Math.random() * 20);
		String k�rzel = getInstance().createNeuesK�rzel(vorname2, nachname2);

		SpielerI spielerNeu = new SpielerBuilder().setzeK�rzel(k�rzel).setzeGeschlecht(Geschlecht.MAENNLICH)
				.setzeVorname(vorname2).setzeNachname(nachname2).setzePunktezahl(punkte2).build();
		int count = getInstance().erzeugeNeuenSpieler(spielerNeu);
		assertEquals(count, 1);

		SpielerI spielerCheck = getInstance().ladeSpieler(k�rzel);
		assertNotNull("Geladener Spieler darf nicht null sein !", spielerCheck);
		assertEquals(spielerNeu.getK�rzel(), spielerCheck.getK�rzel());
		assertEquals(spielerNeu.getVorname(), spielerCheck.getVorname());
		assertEquals(spielerNeu.getNachname(), spielerCheck.getNachname());
		assertEquals(spielerNeu.getPunktezahl(), spielerCheck.getPunktezahl());
	}

	@Test
	public void sucheDatensatzMitPunktezahl() throws Exception {
		String vorname3 = "Sebastian";
		String nachname3 = "Suche";
		getInstance().deleteAlleK�rzel(vorname3, nachname3);
		for (int punkte3 = 0; punkte3 <= 99; ++punkte3) {
			String k�rzel = getInstance().createNeuesK�rzel(vorname3, nachname3);
			SpielerI spielerNeu = new SpielerBuilder().setzeK�rzel(k�rzel)
					.setzeGeschlecht((punkte3 & 1) == 0 ? Geschlecht.MAENNLICH : Geschlecht.WEIBLICH)
					.setzeVorname(vorname3).setzeNachname(nachname3).setzePunktezahl(punkte3).build();
			assertEquals(1, getInstance().erzeugeNeuenSpieler(spielerNeu));
		}

		int suchPunkte = 98;

		SpielerI spieler = getInstance().sucheSpielerEinfach(vorname3, nachname3, suchPunkte);
		assertNotNull("Gesuchter Spieler darf nicht NULL sein !", spieler);
		assertEquals(suchPunkte, spieler.getPunktezahl());

		spieler = getInstance().sucheSpielerSchneller(vorname3, nachname3, suchPunkte);
		assertNotNull("Gesuchter Spieler darf nicht NULL sein !", spieler);
		assertEquals(suchPunkte, spieler.getPunktezahl());
		
		spieler = getInstance().sucheSpielerSchnellerUndEleganter(vorname3, nachname3, suchPunkte);
		assertNotNull("Gesuchter Spieler darf nicht NULL sein !", spieler);
		assertEquals(suchPunkte, spieler.getPunktezahl());
	}
}
