package de.javawebstatt.tt.wettkampf;

import static java.lang.Math.*;

import java.time.LocalTime;

public class WettkampfSpielSatz {
	
	private WettkampfSpiel spiel; 
	
	private int heimScore = 0, gastScore = 0;
	private LocalTime startTime;
	private LocalTime endTime;

	WettkampfSpielSatz(WettkampfSpiel spiel) {
		this.spiel = spiel;
	}

	public boolean isZuEnde() {
		return (heimScore >= 11 || gastScore >= 11) && abs(heimScore - gastScore) >= 2;
	}

	public void punktFürHeim() {
		heimScore++;
	}

	public void punktFürGast() {
		gastScore++;
	}

	public boolean isHeimGewonnen() {
		return isZuEnde() && (heimScore > gastScore);
	}

	public boolean isGastGewonnen() {
		return isZuEnde() && (gastScore > heimScore);
	}

	public final int getHeimScore() {
		return heimScore;
	}

	public final int getGastScore() {
		return gastScore;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public final LocalTime getStartTime() {
		return startTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime; 
	}

	public final LocalTime getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "WettkampfSpielSatz [start=" + startTime + ", Egebnis=" + heimScore + ":" + gastScore
				+ ", ende=" + endTime + "]";
	}

}
