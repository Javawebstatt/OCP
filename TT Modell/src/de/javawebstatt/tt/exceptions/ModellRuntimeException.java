package de.javawebstatt.tt.exceptions;

public class ModellRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459399991167890813L;
	public static final ModellRuntimeException NAME_EMPTY = new ModellRuntimeException(
			"Kann Spieler nicht builden, Name nicht richtig!");

	public static final ModellRuntimeException MAX_REACHED = new ModellRuntimeException(
			"Kann Element nicht hinzufügen, maximale Anzahl wäre überschritten!");

	public ModellRuntimeException(String message) {
		super(message);
	}

}
