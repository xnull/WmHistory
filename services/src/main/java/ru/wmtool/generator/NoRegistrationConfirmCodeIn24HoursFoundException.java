package ru.wmtool.generator;

import ru.wmtool.security.registration.RegistrationConfirmCode;

public class NoRegistrationConfirmCodeIn24HoursFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5895610431790394751L;
	private RegistrationConfirmCode registrationConfirmCode;
	public NoRegistrationConfirmCodeIn24HoursFoundException() {
		super("В базе нет такой записи активации за последние 24 часа");
	}
	
	public NoRegistrationConfirmCodeIn24HoursFoundException(RegistrationConfirmCode code) {
		this();
		registrationConfirmCode = code;
	}

	public RegistrationConfirmCode getRegistrationConfirmCode() {
		return registrationConfirmCode;
	}
}
