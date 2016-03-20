package de.javawebstatt.tt.wettkampf;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;

public class WettkampfSpielTest {

	@Test
	public void ablaufWettkampfSpielZweiSpieler() throws Exception {
		SpielerI heim = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Axel")
				.setzeNachname("Schwei�").setzePunktezahl(8).build();
		SpielerI gast = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Hans")
				.setzeNachname("Wurst").setzePunktezahl(2).build();
		WettkampfSpiel wks = new WettkampfSpiel(heim, gast);
		wks.setBallwechselDauer(3);

		WettkampfSpielErgebnis ergebnis = wks.trageSpielAus();
		List<WettkampfSpielSatz> s�tze = ergebnis.getS�tze();

		System.out.println(s�tze);
		assertNotNull(s�tze);
		assertTrue("Anzahl S�tze mindestens 3", s�tze.size() >= 3);
		assertTrue("Anzahl S�tze maximal 5", s�tze.size() <= 5);

		int heimS�tze = 0, gastS�tze = 0;
		for (WettkampfSpielSatz satz : s�tze) {
			assertTrue("Satz zu Ende", satz.isZuEnde());
			assertTrue("Differenz innerhalb der S�tze gr��er 2",
					Math.abs(satz.getGastScore() - satz.getHeimScore()) >= 2);
			assertTrue("Satz mit mindestens 11 gewonnen", satz.getGastScore() >= 11 || satz.getHeimScore() >= 11);
			assertTrue("Wenn eine Punktezahl gr��er 11, dann genau 2 Abstand",
					(satz.getGastScore() <= 11 && satz.getHeimScore() <= 11)
							|| Math.abs(satz.getGastScore() - satz.getHeimScore()) == 2);
			assertEquals("Wenn Heim gewonnen, dann mehr Punkte", satz.isHeimGewonnen(),
					satz.getHeimScore() >= satz.getGastScore());
			assertEquals("Wenn Gast gewonnen, dann mehr Punkte", satz.isGastGewonnen(),
					satz.getGastScore() >= satz.getHeimScore());
			assertTrue("Nur Einer gewonnen", satz.isHeimGewonnen() ^ satz.isGastGewonnen());
			if (satz.isHeimGewonnen())
				heimS�tze++;
			else
				gastS�tze++;
		}
		assertTrue("Nur 3 Gewinns�tze", (heimS�tze == 3 || gastS�tze == 3) && (heimS�tze + gastS�tze <= 5));
	}

}
