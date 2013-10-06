package ru.wmtool.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.dao.RegistrationConfirmationCodeDao;
import ru.wmtool.generator.RegistrationConfirmCodeGenerator;
import ru.wmtool.interfaces.MessageSenderService;
import ru.wmtool.interfaces.MesssageSendingFailedException;
import ru.wmtool.message.registration.RegistrationConfirmMessageBuilder;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.sender.IMessageSender;

/**
 * Реализация сервиса отправки сообщения с кодом регистрации
 * 
 * @author Tanyadem
 * 
 */
@Transactional(propagation = Propagation.REQUIRED)
public class RegistrationConfirmCodeSenderService implements
		MessageSenderService {

	@LoggerIn
	private static Logger log;
	@Autowired
	private IMessageSender messageSender;
	@Autowired
	private RegistrationConfirmMessageBuilder messageBuilder;
	@Autowired
	private RegistrationConfirmCodeGenerator codeGenerator;
	@Autowired
	private RegistrationConfirmationCodeDao codeDao;

	/**
	 * Отправляет сообщение о регистрации на адрес пользователя. Сохраняет код
	 * регистрации в бд с флагом used = false
	 * 
	 * @throws MesssageSendingFailedException
	 * @see ru.wmtool.interfaces.MessageSenderService#send(ru.wmtool.security.User)
	 */
	public void send(User user) throws MesssageSendingFailedException {
		log.info("Отправка сообщения о регистрации на адрес пользователя: "
				+ user);
		if (user == null) {
			throw new IllegalArgumentException(
					"Параметр user не может быть null");
		}
		try {
			RegistrationConfirmCode confirmCode = codeGenerator
					.generateCode(user);
			String message = messageBuilder.buildMessage(user, confirmCode);
			messageSender.sendMessage(user, message);
			codeDao.saveConfirmCode(confirmCode);
		} catch (Exception e) {
			throw new MesssageSendingFailedException(user, e);
		}

	}

}