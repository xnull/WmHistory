package ru.wmtool.generator.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.generator.RegistrationConfirmCodeGenerator;
import ru.wmtool.generator.UserDataNotDefinedException;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;

/**
 * @author Maxim Angelov
 * 
 * @pdOid 4d49e470-6759-4252-a900-e609ef7a6af6
 */
public class RegistrationConfirmCodeGeneratorImpl implements
		RegistrationConfirmCodeGenerator {

	@LoggerIn
	private static Logger log;

	/**
	 * @param user
	 * @throws NoSuchAlgorithmException
	 * @throws UserDataNotDefinedException
	 * @pdOid a1224ad0-6ca8-4e99-8f6f-3b7983340406
	 */
	public RegistrationConfirmCode generateCode(User user)
			throws NoSuchAlgorithmException, UserDataNotDefinedException {

		if (user.getContact() == null) {
			throw new UserDataNotDefinedException("user Contact not defined");
		}

		if (user.getContact().getEmail() == null) {
			throw new UserDataNotDefinedException("user mail not defined");
		}

		RegistrationConfirmCode result = new RegistrationConfirmCode();
		result.setUser(user);
		result.setUsed(false);
		result.setRegistrationDate(new Timestamp(new Date().getTime()));
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			md.reset();
			md.update((user.getContact().getEmail() + result.getRegistrationDate().toString()).getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			result.setConfirmCode(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			log.error("algorithm 'MD5' not exist", e);
			throw e;
		} catch (Exception e) {
			log.error("illegal state", e);
			throw new IllegalStateException(e);
		}
		
		return result;
	}
}