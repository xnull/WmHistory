package ru.wmtool.auth;

import org.springframework.security.core.Authentication;
import org.zkoss.spring.security.SecurityUtil;

import ru.wmtool.security.User;

/**
 * Работа с пользователями
 * @author null
 *
 */
public class UserService {
	
	public static User getCurrentUser(){
		Authentication auth = SecurityUtil.getAuthentication();
		return null;
	}

}
