package ru.wmtool.webmoney.signer;

import java.io.UnsupportedEncodingException;

import webmoney.cryptography.Signer;
/**
 * 
 * @author Maxim
 * Класс для подписи запроса, данные (exponent, modulus) взяты из
 * kwm ключа Славика при помощи "wm_keys_extractor.exe". 
 */
public class WmSignerImpl implements WmSigner {
	
	private byte[] modulus = new byte[] { (byte) 0xE1, (byte) 0xB1,
			(byte) 0x2E, (byte) 0x2B, (byte) 0x69, (byte) 0xEE, (byte) 0xA8,
			(byte) 0x8E, (byte) 0x9D, (byte) 0xD1, (byte) 0x63, (byte) 0xD9,
			(byte) 0xFB, (byte) 0x62, (byte) 0xED, (byte) 0x16, (byte) 0x6D,
			(byte) 0x22, (byte) 0x2F, (byte) 0x3D, (byte) 0x27, (byte) 0xDD,
			(byte) 0xE2, (byte) 0x0A, (byte) 0xEB, (byte) 0x1B, (byte) 0x0A,
			(byte) 0x1D, (byte) 0xF5, (byte) 0x5C, (byte) 0x1F, (byte) 0x8E,
			(byte) 0xFE, (byte) 0x95, (byte) 0x61, (byte) 0x1A, (byte) 0x74,
			(byte) 0x2E, (byte) 0xFC, (byte) 0x10, (byte) 0x4A, (byte) 0x85,
			(byte) 0x5A, (byte) 0x0E, (byte) 0x9E, (byte) 0x50, (byte) 0x97,
			(byte) 0xA3, (byte) 0x2E, (byte) 0x49, (byte) 0xEF, (byte) 0x62,
			(byte) 0xF1, (byte) 0x9D, (byte) 0x2C, (byte) 0xB0, (byte) 0x87,
			(byte) 0x49, (byte) 0x4F, (byte) 0x45, (byte) 0xB4, (byte) 0x7B,
			(byte) 0x49, (byte) 0xBC, (byte) 0x14, (byte) 0x03 };
	private byte[] exponent = new byte[] { (byte) 0x91, (byte) 0x7C,
			(byte) 0xCE, (byte) 0x0E, (byte) 0x79, (byte) 0xDE, (byte) 0x48,
			(byte) 0x72, (byte) 0xEA, (byte) 0xF1, (byte) 0x68, (byte) 0x70,
			(byte) 0xBE, (byte) 0xAE, (byte) 0xCC, (byte) 0xBB, (byte) 0x71,
			(byte) 0xCF, (byte) 0xFF, (byte) 0x66, (byte) 0xD4, (byte) 0x83,
			(byte) 0x45, (byte) 0xA3, (byte) 0xEF, (byte) 0x03, (byte) 0x3D,
			(byte) 0x17, (byte) 0x23, (byte) 0xB3, (byte) 0x8D, (byte) 0x21,
			(byte) 0xA1, (byte) 0x8F, (byte) 0x51, (byte) 0x9E, (byte) 0x34,
			(byte) 0x17, (byte) 0xAA, (byte) 0x65, (byte) 0xB4, (byte) 0x96,
			(byte) 0x62, (byte) 0xB3, (byte) 0x82, (byte) 0x88, (byte) 0x2E,
			(byte) 0x4C, (byte) 0x09, (byte) 0xEB, (byte) 0xEC, (byte) 0xB7,
			(byte) 0x9B, (byte) 0x01, (byte) 0x62, (byte) 0x36, (byte) 0x0A,
			(byte) 0x51, (byte) 0xF6, (byte) 0xDC, (byte) 0xF6, (byte) 0x1D,
			(byte) 0x9A, (byte) 0x3A };

	/**
	 * Подписать (зашифровать) текстовую строку, ключами webmoney
	 */
	@Override
	public String sign(String plainStr) {
		String result=null;
		Signer signer = new Signer(exponent, modulus);

		try {
			result = signer.sign(plainStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
