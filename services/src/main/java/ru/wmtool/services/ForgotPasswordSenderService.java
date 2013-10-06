package ru.wmtool.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.dao.RegistrationConfirmationCodeDao;
import ru.wmtool.generator.RegistrationConfirmCodeGenerator;
import ru.wmtool.interfaces.MessageSenderService;
import ru.wmtool.message.registration.RegistrationConfirmMessageBuilder;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.sender.IMessageSender;

@Transactional(propagation = Propagation.REQUIRED)
public class ForgotPasswordSenderService implements MessageSenderService {

	@LoggerIn
	private static Logger log;
	@Autowired
	private RegistrationConfirmCodeGenerator codeGenerator;
	@Autowired
	private RegistrationConfirmMessageBuilder messageBuilder;
	@Autowired
	private RegistrationConfirmationCodeDao codeDao;
	@Autowired
	private IMessageSender messageSender;

	@Override
	public void send(User user) {
		if (user == null) {
			throw new IllegalArgumentException(
					"Отправка письма не может быть осуществлена, т.к. параметр user null");
		}
		log.info("Отправка письма для восстановления пароля. Пользователь "
				+ user);
		try {
			RegistrationConfirmCode confirmCode = codeGenerator
					.generateCode(user);
			String message = messageBuilder.buildMessage(user, confirmCode);
			messageSender.sendMessage(user, message);
			codeDao.saveConfirmCode(confirmCode);
			user.setEnabled(false);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

}
