package de.javawebstatt.umgebung;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.javawebstatt.tt.liga.LigaI;
import de.javawebstatt.tt.verein.Mannschaft;

public class VereineUndSpielerUtil {

	private VereineUndSpielerUtil() {
		// TODO Auto-generated constructor stub
	}
	public static List<Mannschaft> getMannschaften(VereineUndSpieler vus, LigaI liga) {
		List<Mannschaft> mannschaften = vus.vereinsDeque.parallelStream()
				.filter(v -> v.getStadt().getLandesliga().equals(liga))
				.flatMap(v -> v.getMannschaften().parallelStream()).collect(Collectors.toCollection(ArrayList::new));

		return mannschaften;
	}


}
