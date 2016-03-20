package de.javawebstatt.helper;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Stadt;
import de.javawebstatt.tt.verein.Verein;
import de.javawebstatt.umgebung.VereineUndSpieler;
import de.javawebstatt.umgebung.VereineUndSpielerPersist;

public class ReadTxtFilesTest {

	private static final int SIZE_OF_FRAUEN_LISTE = 133;
	private static final int SIZE_OF_MÄNNERLISTE = 133;
	private List<SpielerI> männerList;
	private List<SpielerI> frauenList;

	@Before
	public void ladeListen() {
		Path pathToFile = Paths.get("data/namen/vornamen_männer.txt");
		männerList = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.MAENNLICH);		
		pathToFile = Paths.get("C:\\Entwicklung\\GIT\\javawebstatt\\OCP\\TT Umgebung\\data\\namen\\vornamen_frauen.txt");
		frauenList = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.WEIBLICH);		
	}

	@Test
	public void easyFileTest() {
		Path pathToFile = Paths.get("data/test/one_entry.txt");
//		System.out.println(pathToFile.toAbsolutePath());
//		System.out.println(Charset.defaultCharset() + "/" + Charset.availableCharsets());
		List<SpielerI> list = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.MAENNLICH);	
		assertEquals(2, list.size());
	}

	@Test
	public void sizeOfMännerListe() {
		assertEquals(273, männerList.size());
	}

	@Test
	public void sizeOfFrauenListe() {
		assertEquals(265, frauenList.size());
	}

	@Test
	public void bildeLeereVereine() {
		Random rand = new Random();
		VereineUndSpieler basicVUS = new VereineUndSpieler();
		Arrays.stream(Stadt.values()).map(s -> new Verein(s, "SV " + s.getName() + " " + (rand.nextInt(110) + 1900))).forEach(v -> basicVUS.addVerein(v));
		Arrays.stream(Stadt.values()).map(s -> new Verein(s, s.getName() + " Spaß e.V.")).forEach(v -> basicVUS.addVerein(v));

		VereineUndSpielerPersist.speichern(basicVUS, "bildeLeereVereine", "test");
		assertEquals(38, basicVUS.getVereinsListe().size());

	}
	@Test
	public void fülleVereineMitMitgliedern() {
		Random rand = new Random();
		VereineUndSpieler basicVUS = VereineUndSpielerPersist.laden("bildeLeereVereine", "test");
		
	}
}
