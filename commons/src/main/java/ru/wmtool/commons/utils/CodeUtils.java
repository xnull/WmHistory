package ru.wmtool.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import ru.wmtool.commons.logger.LoggerIn;

public class CodeUtils {

	@LoggerIn
	private static Logger log;

	public static String generateMD5(String str)
			throws NoSuchAlgorithmException {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("md5");
			md.reset();
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("algorithm 'MD5' not exist", e);
			throw e;
		} catch (Exception e) {
			log.error("illegal state", e);
			throw new IllegalStateException(e);
		}
		return sb.toString();
	}

	public static String generateSHA256(String str)
			throws NoSuchAlgorithmException {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("algorithm 'MD5' not exist", e);
			throw e;
		} catch (Exception e) {
			log.error("illegal state", e);
			throw new IllegalStateException(e);
		}
		return sb.toString();
	}
}
