package de.javawebstatt.tt.wettkampf;

import java.time.LocalDateTime;
import java.util.List;

public class WettkampfErgebnis {
	private Wettkampf wettkampf;
	private Integer ergebnisHeim;
	private Integer ergebnisGast;
	private LocalDateTime startZeit; 
	private LocalDateTime endZeit; 
	private List<WettkampfSpiel> spiele;

	public WettkampfErgebnis(Wettkampf wettkampf, List<WettkampfSpiel> spiele) {
		this.wettkampf = wettkampf;
		this.spiele = spiele;
	}

	public final int getErgebnisHeim() {
		if(ergebnisHeim == null) 
			ergebnisHeim =  (int) spiele.stream().filter(wks -> wks.getErgebnis().isHeimGewonnen()).count(); 
		return ergebnisHeim;
	}

	public final int getErgebnisGast() {
		if(ergebnisGast == null) 
			ergebnisGast =  (int) spiele.stream().filter(wks -> wks.getErgebnis().isGastGewonnen()).count(); 
		return ergebnisGast;
	}

	public final LocalDateTime getStartZeit() {
		if(startZeit == null)
			startZeit = spiele.stream().map(wks -> wks.getStartZeit()).min((d1, d2) -> d1.compareTo(d2)).get(); 
		return startZeit;
	}

	public final LocalDateTime getEndZeit() {
		if(endZeit == null)
			endZeit = spiele.stream().map(wks -> wks.getStartZeit()).max((d1, d2) -> d1.compareTo(d2)).get(); 
		return endZeit;
	}

	public final List<WettkampfSpiel> getSpiele() {
		return spiele;
	}

	public boolean isHeimGewonnen() {
		return getErgebnisHeim() > getErgebnisGast();
	}

	public boolean isGastGewonnen() {
		return getErgebnisHeim() < getErgebnisGast();
	}

	public String toStringShort() {
		return getErgebnisHeim() + ":" + getErgebnisGast();
	}

	@Override
	public String toString() {
		return "WettkampfErgebnis [wettkampf=" + wettkampf + ", getErgebnisHeim()=" + getErgebnisHeim()
				+ ", getErgebnisGast()=" + getErgebnisGast() + ", getStartZeit()=" + getStartZeit() + ", getEndZeit()="
				+ getEndZeit() + "]";
	}
}
