package br.unb.cic.iris.core.exception;

/***
 * added by dPersistenceRelational
 */
public class DBException extends EmailException {
	private static final long serialVersionUID = 1L;

	public DBException(String message, Exception cause) {
		super(message, cause);
	}
}