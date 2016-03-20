package de.javawebstatt.tt.wettkampf;

import java.util.List;

public class WettkampfSpielErgebnis {

	private WettkampfSpiel wettkampfSpiel;
	private int heimSatzScore;
	private int gastSatzScore;
	private List<WettkampfSpielSatz> sätze;

	WettkampfSpielErgebnis(WettkampfSpiel wettkampfSpiel, int heimSatzScore, int gastSatzScore,
			List<WettkampfSpielSatz> sätze) {

		this.wettkampfSpiel = wettkampfSpiel;
		this.heimSatzScore = heimSatzScore;
		this.gastSatzScore = gastSatzScore;
		this.sätze = sätze;
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

	public final List<WettkampfSpielSatz> getSätze() {
		return sätze;
	}

	public String toStringShort() {
		return heimSatzScore + ":" + gastSatzScore;
	}

	@Override
	public String toString() {
		return "WettkampfSpielErgebnis [heimSatzScore=" + heimSatzScore + ", gastSatzScore=" + gastSatzScore
				+ ", sätze=" + sätze + "]";
	}

}
