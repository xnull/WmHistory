package ru.wmtool.generator;

public class UnableSavePasswordException extends Exception {

	public UnableSavePasswordException(String message, Throwable initialCause) {
		super(message);
		initCause(initialCause);
	}

	private static final long serialVersionUID = -179420193513661609L;

}
