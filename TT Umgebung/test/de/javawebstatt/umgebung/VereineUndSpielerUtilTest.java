package de.javawebstatt.umgebung;

import static org.junit.Assert.*;

import java.util.Deque;
import java.util.List;

import org.junit.Test;

import de.javawebstatt.tt.liga.Landesliga;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Mannschaft;
import de.javawebstatt.tt.verein.Verein;

public class VereineUndSpielerUtilTest {

	@Test
	public void test() {
		VereineUndSpieler basicVUS = VereineUndSpielerPersist.laden("ladeVereineUndBildeMannschaften", "test");
		List<Mannschaft> mannschaften = VereineUndSpielerUtil.getMannschaften(basicVUS, Landesliga.BAWÜ);
		System.out.println(mannschaften);
		assertEquals(21, mannschaften.size()); 
	}

}
