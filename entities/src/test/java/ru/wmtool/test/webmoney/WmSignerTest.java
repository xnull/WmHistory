package ru.wmtool.test.webmoney;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ru.wmtool.webmoney.signer.WmSigner;
import ru.wmtool.webmoney.signer.WmSignerImpl;

/**
 * Тестирование класса для подписи строки, ключами webmoney
 * @author null
 *
 */
public class WmSignerTest {
	private WmSigner signer;
	
	@Before
	public void setUp(){
		signer = new WmSignerImpl();
	}	
	
	@Test
	public void testSignString(){
		int expectedSignedStringLenght = 132;
		String actualSignedString = signer.sign("test");
		
		assertEquals(expectedSignedStringLenght, actualSignedString.length());
	}
}
