package de.javawebstatt.tt.liga;

import java.util.Set;

public class Landesliga extends AbstractLiga {

	public static final Landesliga SCHLESWIG_HOLSTEIN = new Landesliga("Schleswig Holstein");
	public static final Landesliga NIEDERSACHSEN = new Landesliga("Niedersachsen");
	public static final Landesliga BERLIN_BRANDENBURG = new Landesliga("Berlin Brandenburg");

	public static final Landesliga PFALZ = new Landesliga("Rheinland Pfalz");
	public static final Landesliga NRW = new Landesliga("NRW");
	public static final Landesliga HESSEN = new Landesliga("Hessen");
	public static final Landesliga SACHSEN = new Landesliga("Sachsen");

	public static final Landesliga BAWÜ = new Landesliga("Baden-Württemberg");
	public static final Landesliga BAYERN = new Landesliga("Bayern");

	public Landesliga(String name) {
		super(name, 20);
	}

	@Override
	protected Set<? extends AbstractLiga> createKindLigen() {
		return null;
	}

	public Streifenliga getLandesliga() {
		return (Streifenliga) super.getVaterLiga();
	}

}
