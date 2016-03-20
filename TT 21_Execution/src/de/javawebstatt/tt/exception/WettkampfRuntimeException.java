package de.javawebstatt.tt.exception;

public class WettkampfRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	/**
	 * 
	 */
	public static final RuntimeException MODUS_NOT_SUPPORTED = new WettkampfRuntimeException("Modus nicht unterstzt !");
	/**
	 * 
	 */
	public static final RuntimeException ALREADY_PLAYED = new WettkampfRuntimeException("Der Wettkampf wurde bereits ausgetragen !");
	/**
	 * 
	 */
	public static final RuntimeException NOT_YET_FINISHED = new WettkampfRuntimeException("Der Wettkampf ist noch nicht zu Ende !");
	
	
	public WettkampfRuntimeException(String message) {
		super(message);
	}

}
