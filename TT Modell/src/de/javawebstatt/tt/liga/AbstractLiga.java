package de.javawebstatt.tt.liga;

import java.util.Set;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.verein.Mannschaft;

public abstract class AbstractLiga implements LigaI {

	private LigaI vaterLiga;
	private Set<? extends LigaI> kindLigen;
	private Set<Mannschaft> mannschaften; 

	private String name;
	private int maxMannschaften; 

	public AbstractLiga(String name, int maxMannschaften) {
		super();
		this.name = name;
		this.maxMannschaften = maxMannschaften; 
		kindLigen = createKindLigen(); 
	}

	protected abstract Set<? extends AbstractLiga> createKindLigen(); 
	
	@Override
	public LigaI getVaterLiga() {
		return vaterLiga;
	}

	@Override
	public void setVaterLiga(LigaI vaterLiga) {
		this.vaterLiga = vaterLiga;
	}

	@Override
	public Set<? extends LigaI> getKindLigen() {
		return kindLigen;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractLiga) {
			LigaI otherLiga = (LigaI) obj;
			return name.equals(otherLiga.getName());
		} else
			return false;
	}

	@Override
	public String toString() {
		return "AbstractLiga [name=" + name + ", vaterLiga(name)=" + (vaterLiga != null ? vaterLiga.getName() : null) + ", \n\tkindLigen=" + kindLigen + "]";
	}

	@Override
	public final int getMaxMannschaften() {
		return maxMannschaften;
	}

	@Override
	public void addMannschaft(Mannschaft mannschaft) {
		if(mannschaften.size() >= maxMannschaften) 
			throw ModellRuntimeException.MAX_REACHED; 
		mannschaften.add(mannschaft);
	}

}
