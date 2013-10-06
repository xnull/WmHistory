package ru.wmtool.test.generator;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import ru.wmtool.generator.RegistrationConfirmCodeGenerator;
import ru.wmtool.generator.UserDataNotDefinedException;
import ru.wmtool.generator.impl.RegistrationConfirmCodeGeneratorImpl;
import ru.wmtool.security.User;

public class RegistrationConfirmCodeGeneratorTest {
	
	User normalUser;
	User badUser;
	
	void setUp(){
		normalUser = new User();
		normalUser.setName("TestName");
		normalUser.setPassword("TestPassword");
		normalUser.setUsername("TestUserName");
		badUser = new User();
	}
	
	@Test
	void generateCodeTest(){
		RegistrationConfirmCodeGenerator generator = new RegistrationConfirmCodeGeneratorImpl();
		
		try {
			generator.generateCode(normalUser);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserDataNotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
