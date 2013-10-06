package ru.wmtool.web.register.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.dao.EmptyResultException;
import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.generator.UserService;
import ru.wmtool.interfaces.MessageSenderService;
import ru.wmtool.interfaces.MesssageSendingFailedException;
import ru.wmtool.security.User;
import ru.wmtool.web.register.ForgotPasswordPresenter;
import ru.wmtool.web.register.ForgotPasswordView;
import ru.wmtool.web.register.RegisterBuilder;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter {

	@SuppressWarnings("unused")
	private RegisterBuilder registerBuilder;
	@SuppressWarnings("unused")
	private ForgotPasswordView forgotPasswordView;
	@Autowired
	private MessageSenderService forgotPasswordSenderService;

	@Autowired
	private UserService userService;

	@LoggerIn
	private static Logger log;

	@Override
	public void setView(ForgotPasswordView forgotPasswordView) {
		this.forgotPasswordView = forgotPasswordView;

	}

	@Override
	public void setRegisterBuilder(RegisterBuilder registerBuilder) {
		this.registerBuilder = registerBuilder;
	}

	@Transactional
	@Override
	public void sendLetter(String email) throws EmptyResultException,
			NotSingleResultException, MesssageSendingFailedException {
		log.debug("Запуск процедуры отправки письма при забытом пароле");
		User user = userService.getUserByEmail(email);
		forgotPasswordSenderService.send(user);
	}
}
