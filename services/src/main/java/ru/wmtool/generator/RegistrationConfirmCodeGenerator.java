package ru.wmtool.generator;

/***********************************************************************
 * Module:  IWmRegistrationConfirmCodeGenerator.java
 * Author:  Maxim
 * Purpose: Defines the Interface IWmRegistrationConfirmCodeGenerator
 ***********************************************************************/

import java.security.NoSuchAlgorithmException;

import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;

/**
 * Генератор кода подтверждения регистрации для пользователя.
 * 
 * @pdOid 75206a3e-e396-42a6-af52-d4328a0a8939
 */
public interface RegistrationConfirmCodeGenerator {
	/**
	 * Генерация кода подтверждения.
	 * 
	 * @param user
	 *            данные юзера
	 * @throws NoSuchAlgorithmException
	 * @throws UserDataNotDefinedException
	 * @pdOid 810fb389-5ba7-4b71-bc83-7d29324e0a81
	 */
	RegistrationConfirmCode generateCode(User user)
			throws NoSuchAlgorithmException, UserDataNotDefinedException;

}