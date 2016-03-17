package de.javawebstatt.tt.liga;

import java.util.Set;

import de.javawebstatt.tt.exceptions.ModellRuntimeException;
import de.javawebstatt.tt.verein.Mannschaft;

public abstract class AbstractLiga {

	private AbstractLiga vaterLiga;
	private Set<? extends AbstractLiga> kindLigen;
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
	
	public AbstractLiga getVaterLiga() {
		return vaterLiga;
	}

	public void setVaterLiga(AbstractLiga vaterLiga) {
		this.vaterLiga = vaterLiga;
	}

	public Set<? extends AbstractLiga> getKindLigen() {
		return kindLigen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AbstractLiga) {
			AbstractLiga otherLiga = (AbstractLiga) obj;
			return name.equals(otherLiga.getName());
		} else
			return false;
	}

	@Override
	public String toString() {
		return "AbstractLiga [name=" + name + ", vaterLiga(name)=" + (vaterLiga != null ? vaterLiga.name : null) + ", \n\tkindLigen=" + kindLigen + "]";
	}

	public final int getMaxMannschaften() {
		return maxMannschaften;
	}

	public void addMannschaft(Mannschaft mannschaft) {
		if(mannschaften.size() >= maxMannschaften) 
			throw ModellRuntimeException.MAX_REACHED; 
		mannschaften.add(mannschaft);
	}

}
