package de.javawebstatt.tt.liga;

import java.util.HashSet;
import java.util.Set;

public class Bundesliga extends AbstractLiga {

	public static final Bundesliga BUNDESLIGA = new Bundesliga("Bundesliga");;

	static {
		BUNDESLIGA.addLandesliga(Streifenliga.NORD);
		BUNDESLIGA.addLandesliga(Streifenliga.MITTE);
		BUNDESLIGA.addLandesliga(Streifenliga.SÜD);
	}

	public Bundesliga(String name) {
		super(name, 6);
	}

	@SuppressWarnings("unchecked")
	public Set<Streifenliga> getLandesligen() {
		return (Set<Streifenliga>) super.getKindLigen();
	}

	public void addLandesliga(Streifenliga streifenliga) {
		Set<Streifenliga> landesligen = getLandesligen();
		landesligen.add(streifenliga);
		streifenliga.setVaterLiga(this);
	}

	@Override
	public LigaI getVaterLiga() {
		return null; 
	}

	protected Set<? extends AbstractLiga> createKindLigen() {
		return new HashSet<Streifenliga>();
	}

}
