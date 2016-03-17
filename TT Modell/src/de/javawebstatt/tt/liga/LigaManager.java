package de.javawebstatt.tt.liga;

import de.javawebstatt.tt.verein.Mannschaft;

public class LigaManager {

	public static void addManschaftToRegionalliga(Mannschaft mannschaft, AbstractLiga regionalliga) {
		regionalliga.addMannschaft(mannschaft); 
	}
}
