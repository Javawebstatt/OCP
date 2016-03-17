package de.javawebstatt.tt.verein;

import de.javawebstatt.tt.liga.Landesliga;

public enum Stadt {

	H("Hannover", Landesliga.NIEDERSACHSEN), BS("Braunschweig", Landesliga.NIEDERSACHSEN), B("Berlin",
			Landesliga.BERLIN_BRANDENBURG), S("Stuttgart", Landesliga.BAWÜ);

	private String name;
	private Landesliga landesliga;

	private Stadt(String name, Landesliga liga) {
		this.name = name;
		this.landesliga = liga;
	}

	public final String getName() {
		return name;
	}

	public final Landesliga getLandesliga() {
		return landesliga;
	}

}
