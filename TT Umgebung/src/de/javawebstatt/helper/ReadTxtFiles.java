package de.javawebstatt.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;

public class ReadTxtFiles {

	public static List<SpielerI> readSpieler(Path pathToFile, Geschlecht geschlecht) {

		List<SpielerI> spielerListe = new ArrayList<>();

		try {
			List<String> lines = Files.readAllLines(pathToFile);
			spielerListe = lines.parallelStream().map(s -> s.trim()).filter(s -> s.length() > 0 && !s.startsWith("#"))
					.flatMap(s -> Arrays.stream(s.split("/"))).map(s -> s.trim())
					.map(s -> new SpielerBuilder().setzeGeschlecht(geschlecht).setzeVorname(s).generateNachname())
					.map(sb -> sb.build()).collect(Collectors.toCollection(ArrayList::new));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return spielerListe;
	}
}
