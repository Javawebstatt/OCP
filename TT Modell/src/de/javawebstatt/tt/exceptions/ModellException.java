package de.javawebstatt.tt.exceptions;

public class ModellException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7459396878467890813L;
	public static final ModellException NO_GESCHLECHT = new ModellException(
			"Kann Spieler nicht builden, Geschlecht nicht gesetzt!");

	public ModellException(String message) {
		super(message);
	}

}
