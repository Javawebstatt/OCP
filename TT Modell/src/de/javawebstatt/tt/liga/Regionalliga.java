package de.javawebstatt.tt.liga;

import java.util.Set;

public class Regionalliga extends AbstractLiga {

	public static final Regionalliga SCHLESWIG_HOLSTEIN = new Regionalliga("Schleswig Holstein");
	public static final Regionalliga NIEDERSACHSEN = new Regionalliga("Niedersachsen");
	public static final Regionalliga BERLIN_BRANDENBURG = new Regionalliga("Berlin Brandenburg");

	public static final Regionalliga PFALZ = new Regionalliga("Rheinland Pfalz");
	public static final Regionalliga NRW = new Regionalliga("NRW");
	public static final Regionalliga HESSEN = new Regionalliga("Hessen");
	public static final Regionalliga SACHSEN = new Regionalliga("Sachsen");

	public static final Regionalliga BAWÜ = new Regionalliga("Baden-Württemberg");
	public static final Regionalliga BAYERN = new Regionalliga("Bayern");

	public Regionalliga(String name) {
		super(name, 20);
	}

	@Override
	protected Set<? extends AbstractLiga> createKindLigen() {
		return null;
	}

	public Landesliga getLandesliga() {
		return (Landesliga) super.getVaterLiga();
	}

}
