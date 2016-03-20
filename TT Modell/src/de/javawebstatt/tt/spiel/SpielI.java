package de.javawebstatt.tt.spiel;

import java.time.LocalDateTime;

import de.javawebstatt.tt.spieler.SpielerI;

public interface SpielI {

	SpielerI getHeim();

	SpielerI getGast();

	LocalDateTime getStartZeit();

	LocalDateTime getEndZeit();

}