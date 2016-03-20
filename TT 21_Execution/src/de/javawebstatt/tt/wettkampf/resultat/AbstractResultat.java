package de.javawebstatt.tt.wettkampf.resultat;

import java.time.LocalDateTime;

public class AbstractResultat {

	private LocalDateTime startZeit;
	private StringBuilder protokoll; 

	public void setStartZeit(LocalDateTime startZeit) {
		this.startZeit = startZeit; 
		schreibeProtokoll("Startzeit gesetzt auf " + startZeit);
	}

	public void schreibeProtokoll(String eintrag) {
		protokoll.append(eintrag + "\n"); 
	}
	
	public String getProtokoll(){
		return protokoll.toString(); 
	}
}
