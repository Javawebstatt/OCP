package de.javawebstatt.tt.liga;

import java.util.HashSet;
import java.util.Set;

public class Streifenliga extends AbstractLiga {

	public static final Streifenliga NORD = new Streifenliga("Streifenliga Nord");
	public static final Streifenliga MITTE = new Streifenliga("Streifenliga Mitte");
	public static final Streifenliga SÜD = new Streifenliga("Streifenliga Süd");

	static {
		NORD.addRegionalliga(Landesliga.SCHLESWIG_HOLSTEIN);
		NORD.addRegionalliga(Landesliga.NIEDERSACHSEN);
		NORD.addRegionalliga(Landesliga.BERLIN_BRANDENBURG);

		MITTE.addRegionalliga(Landesliga.PFALZ);
		MITTE.addRegionalliga(Landesliga.NRW);
		MITTE.addRegionalliga(Landesliga.HESSEN);
		MITTE.addRegionalliga(Landesliga.SACHSEN);

		SÜD.addRegionalliga(Landesliga.BAWÜ);
		SÜD.addRegionalliga(Landesliga.BAYERN);
	}

	public Streifenliga(String name) {
		super(name, 5);
	}

	@SuppressWarnings("unchecked")
	public Set<Landesliga> getRegionalligen() {
		return (Set<Landesliga>) getKindLigen();
	}

	public Bundesliga getBundesliga() {
		return (Bundesliga) super.getVaterLiga();
	}

	@Override
	protected Set<? extends AbstractLiga> createKindLigen() {
		return new HashSet<Landesliga>();
	}

	public void addRegionalliga(Landesliga landesliga) {
		Set<Landesliga> regionalligen = getRegionalligen();
		regionalligen.add(landesliga);
		landesliga.setVaterLiga(this);
	}

}
