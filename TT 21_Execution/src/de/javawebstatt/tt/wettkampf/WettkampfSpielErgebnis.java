package de.javawebstatt.tt.wettkampf;

import java.util.List;

public class WettkampfSpielErgebnis {

	private WettkampfSpiel wettkampfSpiel;
	private int heimSatzScore;
	private int gastSatzScore;
	private List<WettkampfSpielSatz> s�tze;

	WettkampfSpielErgebnis(WettkampfSpiel wettkampfSpiel, int heimSatzScore, int gastSatzScore,
			List<WettkampfSpielSatz> s�tze) {

		this.wettkampfSpiel = wettkampfSpiel;
		this.heimSatzScore = heimSatzScore;
		this.gastSatzScore = gastSatzScore;
		this.s�tze = s�tze;
	}

	public boolean isHeimGewonnen() {
		return heimSatzScore > gastSatzScore;
	}

	public boolean isGastGewonnen() {
		return heimSatzScore < gastSatzScore;
	}

	public final int getHeimSatzScore() {
		return heimSatzScore;
	}

	public final int getGastSatzScore() {
		return gastSatzScore;
	}

	public final List<WettkampfSpielSatz> getS�tze() {
		return s�tze;
	}

	public String toStringShort() {
		return heimSatzScore + ":" + gastSatzScore;
	}

	@Override
	public String toString() {
		return "WettkampfSpielErgebnis [heimSatzScore=" + heimSatzScore + ", gastSatzScore=" + gastSatzScore
				+ ", s�tze=" + s�tze + "]";
	}

}
