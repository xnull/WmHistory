package ru.wmtool.web.register.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.generator.UserService;
import ru.wmtool.interfaces.MessageSenderService;
import ru.wmtool.interfaces.MesssageSendingFailedException;
import ru.wmtool.security.User;
import ru.wmtool.web.register.RegisterBuilder;
import ru.wmtool.web.register.RegisterPresenter;
import ru.wmtool.web.register.RegisterView;
import ru.wmtool.web.register.SameEmailExistException;

public class RegisterPresenterImpl implements RegisterPresenter {

	@SuppressWarnings("unused")
	private RegisterBuilder registerBuilder;
	@SuppressWarnings("unused")
	private RegisterView registerView;
	@Autowired
	private MessageSenderService registrationConfirmCodeSenderService;

	@Autowired
	private UserService userService;

	@LoggerIn
	private static Logger log;

	@Override
	public void setView(RegisterView registerView) {
		this.registerView = registerView;

	}

	@Override
	public void setRegisterBuilder(RegisterBuilder registerBuilder) {
		this.registerBuilder = registerBuilder;
	}

	@Override
	public void sendLetter(String email) throws SameEmailExistException, MesssageSendingFailedException {
		log.debug("Запуск процедуры отправки письма с активацией");
		if (userService.getContactByEmail(email) != null) {
			throw new SameEmailExistException(
					"В системе уже зарегистрирован пользователь с таким адресом электронной почты "
							+ email, email);
		}
		User user = userService.createAndSaveUser(email);
		registrationConfirmCodeSenderService.send(user);
	}
}
