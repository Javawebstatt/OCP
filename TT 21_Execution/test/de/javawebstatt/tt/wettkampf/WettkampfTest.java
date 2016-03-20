package de.javawebstatt.tt.wettkampf;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt._testhelper.ReadTxtFiles;
import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Mannschaft;
import de.javawebstatt.tt.verein.Stadt;
import de.javawebstatt.tt.verein.Verein;

public class WettkampfTest {

	private Verein verein;
	private Stadt stadt;
	private String vereinsName;
	private Mannschaft heimMannschaft;
	private Mannschaft gastMannschaft;

	@Before
	public void erzeugeVereinUndMannschaften() {
		stadt = Stadt.H;
		vereinsName = "Netter Verein e.v.";
		verein = new Verein(stadt, vereinsName);

		Path pathToFile = Paths.get("data/namen/paar_vornamen.txt");
		List<SpielerI> spielerListe = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.MAENNLICH);
		SpielerI[] spielerArr = new SpielerI[0];

		List<SpielerI> heimSpielerList = spielerListe.stream().limit(4).collect(Collectors.toList());
		heimMannschaft = verein.gründeMannschaft(heimSpielerList.toArray(new SpielerI[0]));
		assertNotNull(heimMannschaft);
		// System.out.println(heimMannschaft);

		List<SpielerI> gastSpielerList = spielerListe.stream().skip(4).limit(4).collect(Collectors.toList());
		gastMannschaft = verein.gründeMannschaft(gastSpielerList.toArray(new SpielerI[0]));
		assertNotNull(gastMannschaft);
		// System.out.println(gastMannschaft);
	}

	@Test
	public void zweiMannschaftenSpielen() throws Exception {
		Wettkampf wk = new Wettkampf(heimMannschaft, gastMannschaft, WettkampfModus.JEDER_GEGEN_JEDEN_NUR_EINZEL);
		wk.setAnzPlatten(3);
		assertEquals(Wettkampf.Status.NEU, wk.getStatus());
		WettkampfErgebnis ergebnis = wk.trageWettkampfAus();
		// System.out.println(spiele);
		assertEquals(Wettkampf.Status.BEENDET, wk.getStatus());
	}

}
