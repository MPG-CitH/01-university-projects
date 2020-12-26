package de.uniba.wiai.dsg.ajp.assignment1.validation;

/**
 * Wrapper Exception for all errors occurring during search.
 */
public class CodeValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public CodeValidationException() {
		super();
	}

	public CodeValidationException(String message) {
		super(message);
	}

	public CodeValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeValidationException(Throwable cause) {
		super(cause);
	}

	protected CodeValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
