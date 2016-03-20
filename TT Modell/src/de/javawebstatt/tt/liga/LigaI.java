package de.javawebstatt.tt.liga;

import java.util.Set;

import de.javawebstatt.tt.verein.Mannschaft;

public interface LigaI {

	void addMannschaft(Mannschaft mannschaft);

	int getMaxMannschaften();

	void setName(String name);

	String getName();

	Set<? extends LigaI> getKindLigen();

	void setVaterLiga(LigaI vaterLiga);

	LigaI getVaterLiga();

}
