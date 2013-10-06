package ru.wmtool.webmoney.signer;

/**
 * Подпись строки текста, ключами wmid-a.
 * 
 * @author null
 * 
 */
public interface WmSigner {

	/**
	 * Подпись текстовой строки.
	 * 
	 * @param plainStr
	 *            строка которая будет подписана ключами wmid-a
	 * @return результирующая подписанная строка.
	 */
	String sign(String plainStr);

}
