package ru.wmtool.web.login.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ru.wmtool.generator.UnableSavePasswordException;
import ru.wmtool.generator.UserService;
import ru.wmtool.security.User;
import ru.wmtool.web.login.LoginBuilder;
import ru.wmtool.web.login.PasswordInputPresenter;
import ru.wmtool.web.login.PasswordInputView;

public class PasswordInputPresenterImpl implements PasswordInputPresenter {

	@SuppressWarnings("unused")
	private PasswordInputView passwordInputView;
	@SuppressWarnings("unused")
	private LoginBuilder loginBuilder;
	@Autowired
	private UserService userService;
	
	@Override
	public void setPasswordInputView(PasswordInputView passwordInputView) {
		this.passwordInputView = passwordInputView;
	}

	@Override
	public void setLoginBuilder(LoginBuilder loginBuilder) {
		this.loginBuilder = loginBuilder;
	}

	@Override
	public void changePassword(User user, String text) throws UnableSavePasswordException {
		userService.savePassword(user, text);
	}

}
