package ru.wmtool.generator;

import ru.wmtool.security.registration.RegistrationConfirmCode;

public class ConfirmCodeAlreadyUsedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2035852454789148517L;
	private RegistrationConfirmCode confirmCode;
	public ConfirmCodeAlreadyUsedException(RegistrationConfirmCode confirmCode) {
		this.confirmCode = confirmCode;
	}
	
	public ConfirmCodeAlreadyUsedException(String message,
			RegistrationConfirmCode confirmCode) {
		super(message);
		this.confirmCode = confirmCode;
	}

	public RegistrationConfirmCode getConfirmCode() {
		return confirmCode;
	}
}
