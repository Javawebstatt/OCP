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
				.setzeNachname("Schweiß").setzePunktezahl(8).build();
		SpielerI gast = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Hans")
				.setzeNachname("Wurst").setzePunktezahl(2).build();
		WettkampfSpiel wks = new WettkampfSpiel(heim, gast);
		wks.setBallwechselDauer(3);

		WettkampfSpielErgebnis ergebnis = wks.trageSpielAus();
		List<WettkampfSpielSatz> sätze = ergebnis.getSätze();

		System.out.println(sätze);
		assertNotNull(sätze);
		assertTrue("Anzahl Sätze mindestens 3", sätze.size() >= 3);
		assertTrue("Anzahl Sätze maximal 5", sätze.size() <= 5);

		int heimSätze = 0, gastSätze = 0;
		for (WettkampfSpielSatz satz : sätze) {
			assertTrue("Satz zu Ende", satz.isZuEnde());
			assertTrue("Differenz innerhalb der Sätze größer 2",
					Math.abs(satz.getGastScore() - satz.getHeimScore()) >= 2);
			assertTrue("Satz mit mindestens 11 gewonnen", satz.getGastScore() >= 11 || satz.getHeimScore() >= 11);
			assertTrue("Wenn eine Punktezahl größer 11, dann genau 2 Abstand",
					(satz.getGastScore() <= 11 && satz.getHeimScore() <= 11)
							|| Math.abs(satz.getGastScore() - satz.getHeimScore()) == 2);
			assertEquals("Wenn Heim gewonnen, dann mehr Punkte", satz.isHeimGewonnen(),
					satz.getHeimScore() >= satz.getGastScore());
			assertEquals("Wenn Gast gewonnen, dann mehr Punkte", satz.isGastGewonnen(),
					satz.getGastScore() >= satz.getHeimScore());
			assertTrue("Nur Einer gewonnen", satz.isHeimGewonnen() ^ satz.isGastGewonnen());
			if (satz.isHeimGewonnen())
				heimSätze++;
			else
				gastSätze++;
		}
		assertTrue("Nur 3 Gewinnsätze", (heimSätze == 3 || gastSätze == 3) && (heimSätze + gastSätze <= 5));
	}

}
