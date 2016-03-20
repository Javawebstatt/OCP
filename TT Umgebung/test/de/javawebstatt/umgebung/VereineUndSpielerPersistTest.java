package de.javawebstatt.umgebung;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import de.javawebstatt.tt._testhelper.ReadTxtFiles;
import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Stadt;
import de.javawebstatt.tt.verein.Verein;
import de.javawebstatt.umgebung.VereineUndSpieler;
import de.javawebstatt.umgebung.VereineUndSpielerPersist;

public class VereineUndSpielerPersistTest {

	private static final int SIZE_OF_FRAUEN_LISTE = 133;
	private static final int SIZE_OF_M�NNERLISTE = 133;
	private List<SpielerI> m�nnerList;
	private List<SpielerI> frauenList;

	@Before
	public void ladeListen() {
		Path pathToFile = Paths.get("C:\\Entwicklung\\GIT\\javawebstatt\\OCP\\TT Umgebung\\data\\namen\\vornamen_m�nner.txt");
		m�nnerList = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.MAENNLICH);		
		pathToFile = Paths.get("C:\\Entwicklung\\GIT\\javawebstatt\\OCP\\TT Umgebung\\data\\namen\\vornamen_frauen.txt");
		frauenList = ReadTxtFiles.readSpieler(pathToFile, Geschlecht.WEIBLICH);		
	}

	@Test
	public void sizeOfM�nnerListe() {
		assertEquals(273, m�nnerList.size());
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
		Arrays.stream(Stadt.values()).map(s -> new Verein(s, s.getName() + " Spa� e.V.")).forEach(v -> basicVUS.addVerein(v));

		VereineUndSpielerPersist.speichern(basicVUS, "bildeLeereVereine", "test");
		assertEquals(38, basicVUS.getVereinsListe().size());

	}

	@Test
	public void ladeVereineUndF�lleMitSpielern() {
		VereineUndSpieler basicVUS = VereineUndSpielerPersist.laden("bildeLeereVereine", "test");

		Stream.of(m�nnerList.stream(), frauenList.stream()).flatMap(s -> s).parallel().forEach(s -> basicVUS.addSpieler(s));
		VereineUndSpielerPersist.speichern(basicVUS, "ladeVereineUndF�lleMitSpielern", "test");
		
		assertEquals(38, basicVUS.getVereinsListe().size());
		assertEquals(538, basicVUS.getSpielerListe().size());
	}

	@Test
	public void ladeVereineUndBildeMannschaften() {
		VereineUndSpieler basicVUS = VereineUndSpielerPersist.laden("ladeVereineUndF�lleMitSpielern", "test");
		Deque<Verein> vereine = basicVUS.getVereinsListe();
		Deque<SpielerI> spieler = basicVUS.getSpielerListe(); 
		int count = 0; 
		while(spieler.size() >= 4){
			Verein verein = vereine.pop();
			SpielerI spieler1 = spieler.pop();
			SpielerI spieler2 = spieler.pop();
			SpielerI spieler3 = spieler.pop();
			SpielerI spieler4 = spieler.pop();
			verein.gr�ndeMannschaft(spieler1, spieler2, spieler3, spieler4);
			vereine.add(verein); 
			System.out.println(++count + ") vereine=" + vereine);
		}
		VereineUndSpielerPersist.speichern(basicVUS, "ladeVereineUndBildeMannschaften", "test");
	}
}
