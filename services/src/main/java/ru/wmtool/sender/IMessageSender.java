package ru.wmtool.sender;

/***********************************************************************
 * Module:  IWmMessageSender.java
 * Author:  Maxim
 * Purpose: Defines the Interface IWmMessageSender
 ***********************************************************************/

import ru.wmtool.security.User;

/**
 * Сендер сообщения.
 * 
 * @pdOid 11555031-9661-4ed2-b094-4415e132b0fe
 */
public interface IMessageSender {
	/**
	 * Отправка сообщения юзеру.
	 * 
	 * @param user
	 *            юзер
	 * @param message
	 *            сообщение
	 * @pdOid d2158f8d-d6d6-41c7-ad00-ed56673ce7c2
	 */
	void sendMessage(User user, String message);

}