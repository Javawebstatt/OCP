package de.javawebstatt.tt.verein;

import static org.junit.Assert.*;

import java.util.function.IntSupplier;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;

public class VereinTest {

	private Verein verein;
	private Stadt stadt;
	private String vereinsName;

	@Before
	public void erzeugeVerein() {
		stadt = Stadt.H;
		vereinsName = "Netter Verein e.v.";
		verein = new Verein(stadt, vereinsName);
	}

	@Test
	public void legeVereinAndUndMitgliedHinzufügen() {
		assertEquals(stadt, verein.getStadt());
		assertEquals(vereinsName, verein.getName());

		SpielerI spieler = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Hans")
				.setzeNachname("Wurst").build();
		verein.addMitglied(spieler);

		assertTrue(verein.getMitglieder().contains(spieler));
		assertEquals(verein, spieler.getVerein());
	}

	@Test
	public void testGründeMannschaft() {
		SpielerI spieler = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Hans")
				.setzeNachname("Wurst").build();
		SpielerI spieler2 = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Peter")
				.setzeNachname("Paul").build();
		SpielerI spieler3 = new SpielerBuilder().setzeGeschlecht(Geschlecht.WEIBLICH).setzeVorname("Marta")
				.setzeNachname("Pfahl").build();
		SpielerI spieler4 = new SpielerBuilder().setzeGeschlecht(Geschlecht.MAENNLICH).setzeVorname("Susi")
				.setzeNachname("Süß").build();

		Mannschaft mannschaft = verein.gründeMannschaft(spieler, spieler2, spieler3, spieler4);

		assertEquals(1, mannschaft.getNummer());
		
		System.out.println(mannschaft);
		
		

	}

}
