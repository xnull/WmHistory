package ru.wmtool.sender.impl;

import org.apache.log4j.Logger;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.User;
import ru.wmtool.sender.IMessageSender;

public class FakeMessageSender implements IMessageSender {
	
	@LoggerIn
	private static Logger log;

	@Override
	public void sendMessage(User user, String message) {
		log.info("Fake-сообщение отправлено");
		log.debug("Для юзера " + user + " со следующим содержанием " + message);
	}

}
