package ru.wmtool.generator;

public class UserDataNotDefinedException extends Exception {
	private static final long serialVersionUID = 7127358354233664852L;

	public UserDataNotDefinedException() {
		super();
	}

	public UserDataNotDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserDataNotDefinedException(String message) {
		super(message);
	}

	public UserDataNotDefinedException(Throwable cause) {
		super(cause);
	}

}
