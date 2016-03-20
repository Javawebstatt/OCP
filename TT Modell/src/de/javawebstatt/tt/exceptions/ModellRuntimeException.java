package de.javawebstatt.tt.exceptions;

public class ModellRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459399991167890813L;
	public static final ModellRuntimeException NAME_EMPTY = new ModellRuntimeException(
			"Kann Spieler nicht builden, Name nicht gesetzt!");

	public static final ModellRuntimeException MAX_REACHED = new ModellRuntimeException(
			"Kann Element nicht hinzufügen, maximale Anzahl wäre überschritten!");

	public static final ModellRuntimeException NO_GESCHLECHT = new ModellRuntimeException(
			"Kann Spieler nicht builden, Geschlecht nicht gesetzt!");

	public static final ModellRuntimeException WRONG_NUM_SPIELER = new ModellRuntimeException(
			"Es wurde die falsche Anzahl an Spielern übergeben");

	public static final RuntimeException PLAYER_ALREADY_PLAYS = new ModellRuntimeException(
			"Der Spieler ist bereits im Spiel !");

	public static final RuntimeException PLAYER_PLAYS_OTHER_GAME = new ModellRuntimeException(
			"Der Spieler spielt ein anderes Spiel !");

	public ModellRuntimeException(String message) {
		super(message);
	}

}
