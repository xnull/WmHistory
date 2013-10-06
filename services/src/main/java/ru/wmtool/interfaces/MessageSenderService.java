package ru.wmtool.interfaces;

/***********************************************************************
 * Module:  IWmMessageSenderService.java
 * Author:  Maxim
 * Purpose: Defines the Interface IWmMessageSenderService
 ***********************************************************************/

import ru.wmtool.security.User;

/**
 * Сервис отправки сообщения.
 * 
 * @pdOid c4c00095-13b3-4918-8448-dfd55bee9881
 */
public interface MessageSenderService {
	/**
	 * Отправка сообщения пользователю.
	 * 
	 * @param user
	 * @throws MesssageSendingFailedException 
	 * @pdOid 15266264-7e55-406e-b127-341f69a17069
	 */
	void send(User user) throws MesssageSendingFailedException;

}