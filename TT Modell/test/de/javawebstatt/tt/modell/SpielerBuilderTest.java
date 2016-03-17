package de.javawebstatt.tt.modell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt.exceptions.ModellException;
import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielStatus;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.spieler.schl�ger.Schl�gerFabrik;
import de.javawebstatt.tt.spieler.schl�ger.Schl�gerI;

public class SpielerBuilderTest {

	private SpielerI standardSpieler;

	@Before
	public void createStandardSpieler() throws ModellException {
		int PUNKTEZAHL = 99;
		String VORNAME = "Paul";
		String NACHNAME = "Weber";
		standardSpieler = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname(VORNAME)
				.setzeNachname(NACHNAME).setzePunktezahl(PUNKTEZAHL).build();
		
	}
	
	@Test
	public void createSpielerWithoutGeschlecht() {
		SpielerI spieler = null;
		try {
			spieler = new SpielerBuilder().build();
		} catch (Exception e) {
			assertSame(e, ModellException.NO_GESCHLECHT);
			;
		}
		assertNull(spieler);
	}

	@Test
	public void createSpielerWithNameUndPunktezahl() throws ModellException {
		assertEquals(standardSpieler.getPunktezahl(), 99);
	}

	@Test
	public void spielerSetzeSchl�gerSpielbereit() {
		Schl�gerI testSchl�ger = Schl�gerFabrik.createSchl�ger("Gewo", "Allround Classic");
		standardSpieler.setSchl�ger(testSchl�ger);
		assertEquals(standardSpieler.getSpielStatus(), SpielStatus.OK);
		assertEquals(standardSpieler.getSchl�ger(), testSchl�ger);
	}
}
