package de.javawebstatt.tt.verein;

import de.javawebstatt.tt.liga.Landesliga;

public enum Stadt {

	H("Hannover", Landesliga.NIEDERSACHSEN), BS("Braunschweig", Landesliga.NIEDERSACHSEN), B("Berlin",
			Landesliga.BERLIN_BRANDENBURG), P("Potsdem", Landesliga.BERLIN_BRANDENBURG), S("Stuttgart",
					Landesliga.BAWÜ), KA("Karlsruhe", Landesliga.BAWÜ), PF("Pforzheim", Landesliga.BAWÜ), M("München",
							Landesliga.BAYERN), N("Nürnberg", Landesliga.BAYERN), F("Frankfurt", Landesliga.HESSEN), FU(
									"Fulda", Landesliga.HESSEN), GI("Gießen", Landesliga.HESSEN), DD("Dresden",
											Landesliga.SACHSEN), PIR("Pirna", Landesliga.SACHSEN), L("Leipzig",
													Landesliga.SACHSEN), KL("Kaiserslautern", Landesliga.PFALZ), K(
															"Köln", Landesliga.NRW), D("Düsseldorf",
																	Landesliga.NRW), BO("Bochum", Landesliga.NRW);

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
