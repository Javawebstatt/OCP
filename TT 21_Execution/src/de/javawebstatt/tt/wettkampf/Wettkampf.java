package de.javawebstatt.tt.wettkampf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.javawebstatt.tt.exception.WettkampfRuntimeException;
import de.javawebstatt.tt.spieler.SpielerI;
import de.javawebstatt.tt.verein.Mannschaft;

public class Wettkampf {

	public enum Status {
		NEU, LÄUFT, BEENDET
	}

	public static final int DEFAULT_ANZAHL_PLATTEN = 2;

	private Mannschaft heimMannschaft;
	private Mannschaft gastMannschaft;
	private WettkampfErgebnis ergebnis;
	private Status status;

	private WettkampfModus modus;

	private int anzPlatten;

	public Wettkampf(Mannschaft heimMannschaft, Mannschaft gastMannschaft, WettkampfModus modus) {
		this.heimMannschaft = heimMannschaft;
		this.gastMannschaft = gastMannschaft;
		this.status = Status.NEU;
		this.modus = modus;
		this.anzPlatten = DEFAULT_ANZAHL_PLATTEN;
	}

	public WettkampfErgebnis trageWettkampfAus() throws Exception {

		if (status != Status.NEU)
			throw WettkampfRuntimeException.ALREADY_PLAYED;

		status = Status.LÄUFT;

		switch (modus) {
		case JEDER_GEGEN_JEDEN_NUR_EINZEL:
			ergebnis = spieleJederGegenJedenNurEinzel4();
			break;
		default:
			throw WettkampfRuntimeException.MODUS_NOT_SUPPORTED;
		}

		status = Status.BEENDET;
		Duration duration = Duration.between(ergebnis.getStartZeit(), ergebnis.getEndZeit());
		System.out.println("[FIGHT '" + heimMannschaft.getName() + "' vs. '" + gastMannschaft.getName() + "' an "
				+ anzPlatten + " endet mit " + ergebnis.toStringShort() + " und dauert " + duration + "]");
		return ergebnis;
	}

	private WettkampfErgebnis spieleJederGegenJedenNurEinzel1() throws Exception {

		SpielerI[] heimSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);
		SpielerI[] gastSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);

		List<WettkampfSpiel> spiele = new ArrayList<>();
		// TODO; Mit Factory erzeugen
		for (int h = 0; h < heimSpieler.length; h++) {
			for (int g = 0; g < gastSpieler.length; g++) {
				if (h != g) {
					WettkampfSpiel spiel = new WettkampfSpiel(heimSpieler[h], gastSpieler[g]);
					spiele.add(spiel);
				}
			}
		}
		ExecutorService exec = Executors.newFixedThreadPool(anzPlatten);

		List<Future<WettkampfSpielErgebnis>> futures = exec.invokeAll(spiele);
		int ergebnisHeim = 0, ergebnisGast = 0;
		for (Future<WettkampfSpielErgebnis> f : futures) {
			try {
				WettkampfSpielErgebnis erg = f.get();
				if (erg.isHeimGewonnen())
					ergebnisHeim++;
				else
					ergebnisGast++;
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		exec.shutdown();
		WettkampfErgebnis privErg = new WettkampfErgebnis(this, spiele);

		return privErg;
	}

	private WettkampfErgebnis spieleJederGegenJedenNurEinzel2() throws Exception {

		SpielerI[] heimSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);
		SpielerI[] gastSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);

		ExecutorService exec = Executors.newFixedThreadPool(anzPlatten);

		List<WettkampfSpiel> spiele = new ArrayList<>();
		List<Future<WettkampfSpielErgebnis>> futures = new ArrayList<>();

		// TODO; Mit Factory erzeugen
		for (int h = 0; h < heimSpieler.length; h++) {
			for (int g = 0; g < gastSpieler.length; g++) {
				if (h != g) {
					WettkampfSpiel spiel = new WettkampfSpiel(heimSpieler[h], gastSpieler[g]);
					spiele.add(spiel);
					// Und gleich raushauen
					Future<WettkampfSpielErgebnis> f = exec.submit(spiel);
					futures.add(f);
				}
			}
		}

		int numOfDone = 0;
		long anzDurchläufe = 0;
		while (numOfDone < futures.size()) {
			anzDurchläufe++;
			for (Future<WettkampfSpielErgebnis> f : futures) {
				if (f.isDone())
					numOfDone++;
			}
		}
		// exec.shutdown();
		System.out.println("Anz. Durchläufe = " + anzDurchläufe);
		WettkampfErgebnis privErg = new WettkampfErgebnis(this, spiele);

		return privErg;
	}

	private WettkampfErgebnis spieleJederGegenJedenNurEinzel3() throws Exception {

		SpielerI[] heimSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);
		SpielerI[] gastSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(anzPlatten);

		List<WettkampfSpiel> spiele = new ArrayList<>();
		List<Future<WettkampfSpielErgebnis>> futures = new ArrayList<>();

		// TODO; Mit Factory erzeugen
		for (int h = 0; h < heimSpieler.length; h++) {
			for (int g = 0; g < gastSpieler.length; g++) {
				if (h != g) {
					WettkampfSpiel spiel = new WettkampfSpiel(heimSpieler[h], gastSpieler[g]);
					spiele.add(spiel);
					// Und gleich raushauen
					Future<WettkampfSpielErgebnis> f = exec.schedule(spiel, 3, TimeUnit.SECONDS);
					futures.add(f);
				}
			}
		}

		for (Future<WettkampfSpielErgebnis> f : futures) {
			try {
				WettkampfSpielErgebnis erg = f.get();
				System.out.println("ERGEBNIS: " + erg);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		exec.shutdown();
		WettkampfErgebnis privErg = new WettkampfErgebnis(this, spiele);

		return privErg;
	}

	private WettkampfErgebnis spieleJederGegenJedenNurEinzel4() throws Exception {

		SpielerI[] heimSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);
		SpielerI[] gastSpieler = heimMannschaft.getSpieler().toArray(new SpielerI[0]);

		ExecutorService exec = Executors.newFixedThreadPool(anzPlatten);

		List<WettkampfSpiel> spiele = new ArrayList<>();
		Queue<WettkampfSpiel> execQueue = new LinkedBlockingQueue<>();
		List<Future<WettkampfSpielErgebnis>> futures = new ArrayList<>();

		// TODO; Mit Factory erzeugen
		for (int h = 0; h < heimSpieler.length; h++) {
			for (int g = 0; g < gastSpieler.length; g++) {
				if (h != g) {
					WettkampfSpiel spiel = new WettkampfSpiel(heimSpieler[h], gastSpieler[g]);
					spiele.add(spiel);
					execQueue.add(spiel);
				}
			}
		}

		// Solange es noch unbearbeitete Spiele gibt, reihum ausführen
		while (!execQueue.isEmpty()) {
			WettkampfSpiel nächstesPotSpiel = execQueue.remove();
			if (!nächstesPotSpiel.getHeim().isInSpiel() && !nächstesPotSpiel.getGast().isInSpiel()) {
				// System.out.println("EXEC JA:" + nächstesPotSpiel);
				Future<WettkampfSpielErgebnis> future = exec.submit(nächstesPotSpiel);
				futures.add(future);
			} else {
				// System.out.println("EXEC GEHT NOCH NICHT:" +
				// nächstesPotSpiel);
				execQueue.add(nächstesPotSpiel);
			}
			Thread.sleep(100);
		}
		
		boolean allDone = false;
		while (!allDone) {
			allDone = true;
			for (Future<WettkampfSpielErgebnis> f : futures) {
				if (!f.isDone())
					allDone = false;
			}
		}

		// CyclicBarrier barrier = new CyclicBarrier(2);
		// barrier.await();

		exec.shutdown();
		WettkampfErgebnis privErg = new WettkampfErgebnis(this, spiele);

		return privErg;
	}

	public final Mannschaft getHeimMannschaft() {
		return heimMannschaft;
	}

	public final Mannschaft getGastMannschaft() {
		return gastMannschaft;
	}

	public final Mannschaft getGewinner() {
		if (status != Status.BEENDET)
			throw WettkampfRuntimeException.NOT_YET_FINISHED;
		return gastMannschaft;
	}

	public final Status getStatus() {
		return status;
	}

	public final WettkampfErgebnis getErgebnis() {
		return ergebnis;
	}

	public int getAnzPlatten() {
		return anzPlatten;
	}

	public void setAnzPlatten(int anzPlatten) {
		this.anzPlatten = anzPlatten;
	}

	@Override
	public String toString() {
		return "Wettkampf [heimMannschaft=" + heimMannschaft + ", gastMannschaft=" + gastMannschaft + ", ergebnis="
				+ ergebnis + ", status=" + status + ", modus=" + modus + ", anzPlatten=" + anzPlatten + "]";
	}

}
