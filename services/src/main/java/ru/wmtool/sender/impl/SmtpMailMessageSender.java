package ru.wmtool.sender.impl;

/***********************************************************************
 * Module:  SmtpMailMessageSender.java
 * Author:  Сервер
 * Purpose: Defines the Class SmtpMailMessageSender
 ***********************************************************************/

import ru.wmtool.security.User;
import ru.wmtool.sender.IMessageSender;

/**
 * Сендер сообщения пользоватею через SMTP Mail.
 * 
 * @pdOid f477806c-2408-45e1-8c26-a5cbdbb3a188
 */
public class SmtpMailMessageSender implements IMessageSender {
	/**
	 * @param user
	 * @param message
	 * @pdOid 1e9481cf-cf59-4df7-9adb-84d1bc104842
	 */
	public void sendMessage(User user, String message) {
		// TODO: implement
	}

}