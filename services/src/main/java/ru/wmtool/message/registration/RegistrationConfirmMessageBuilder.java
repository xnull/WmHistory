package ru.wmtool.message.registration;

/***********************************************************************
 * Module:  IWmRegistrationConfirmMessageBuilder.java
 * Author:  Maxim
 * Purpose: Defines the Interface IWmRegistrationConfirmMessageBuilder
 ***********************************************************************/

import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;

/**
 * Билдер сообщения подтверждения регистрации.
 * 
 * @pdOid 6a30c651-3287-44f2-a31c-89c2e90a60a1
 */
public interface RegistrationConfirmMessageBuilder {
	/**
	 * Составление сообщения.
	 * 
	 * @param user
	 * @param code
	 * @throws Exception
	 * @pdOid 792b0af5-0253-476d-a4ce-7822ff5375c8
	 */
	String buildMessage(User user, RegistrationConfirmCode code)
			throws Exception;

}