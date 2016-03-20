package de.javawebstatt.tt.wettkampf;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import de.javawebstatt.tt.spiel.SpielI;
import de.javawebstatt.tt.spieler.SpielerI;

public class WettkampfSpiel implements Callable<WettkampfSpielErgebnis>, SpielI {

	public static final long BALLWECHSEL_DAUER_DEFAULT = 10;

	private SpielerI heim;
	private SpielerI gast;
	private WettkampfSpielErgebnis ergebnis;
	private LocalDateTime startZeit;
	private LocalDateTime endZeit;
	private long ballwechselDauer;

	public WettkampfSpiel(SpielerI heim, SpielerI gast) {
		this.heim = heim;
		this.gast = gast;
		this.ballwechselDauer = BALLWECHSEL_DAUER_DEFAULT;
	}

	@Override
	public WettkampfSpielErgebnis call() throws Exception {
		return trageSpielAus();
	}

	public WettkampfSpielErgebnis trageSpielAus() throws Exception {

		heim.beginneSpiel(this);
		gast.beginneSpiel(this);
		startZeit = LocalDateTime.now();

		List<WettkampfSpielSatz> sätze = new ArrayList<>();
		Random punkteGenerator = new Random();

		
		int gastSatzScore = 0;
		int heimSatzScore = 0;
		while (heimSatzScore < 3 && gastSatzScore < 3) {
			WettkampfSpielSatz satz = new WettkampfSpielSatz(this);
			satz.setStartTime(LocalTime.now());
			while (!satz.isZuEnde()) {
				try {
					Thread.sleep(ballwechselDauer);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (punkteGenerator.nextBoolean()) {
					satz.punktFürHeim();
					// System.out.println("[P " + heim.getKürzel() + "]");
				} else {
					satz.punktFürGast();
					// System.out.println("[P " + gast.getKürzel() + "]");
				}
			}
			satz.setEndTime(LocalTime.now());
			System.out.println("[SET " + heim.getKürzel() + ":" + gast.getKürzel() + " " + satz.getHeimScore() + ":"
					+ satz.getGastScore() + " " + satz.getStartTime() + "->" + satz.getEndTime() + "]");
			if (satz.isHeimGewonnen())
				heimSatzScore++;
			else
				gastSatzScore++;
			sätze.add(satz);
		}

		endZeit = LocalDateTime.now();
		heim.beendeSpiel(this); 
		gast.beendeSpiel(this);
		
		System.out.println("[GAME " + heim.getKürzel() + ":" + gast.getKürzel() + " " + heimSatzScore + ":"
				+ gastSatzScore + " " + startZeit + "->" + endZeit + "]");
		ergebnis = new WettkampfSpielErgebnis(this, heimSatzScore, gastSatzScore, sätze);
		return ergebnis;
		
	}

	public final void setBallwechselDauer(long ballwechselDauer) {
		this.ballwechselDauer = ballwechselDauer;
	}

	public WettkampfSpielErgebnis getErgebnis() {
		return ergebnis;
	}

	@Override
	public final SpielerI getHeim() {
		return heim;
	}

	@Override
	public final SpielerI getGast() {
		return gast;
	}

	@Override
	public final LocalDateTime getStartZeit() {
		return startZeit;
	}

	@Override
	public final LocalDateTime getEndZeit() {
		return endZeit;
	}

	@Override
	public String toString() {
		return "WettkampfSpiel [heim=" + heim + ", gast=" + gast + ", start=" + startZeit + ", ende=" + endZeit
				+ ", ergebnis=" + ergebnis + "]";
	}

}
