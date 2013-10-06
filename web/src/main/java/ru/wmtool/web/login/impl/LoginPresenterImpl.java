package ru.wmtool.web.login.impl;

import ru.wmtool.web.login.LoginBuilder;
import ru.wmtool.web.login.LoginPresenter;
import ru.wmtool.web.login.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

	@SuppressWarnings("unused")
	private LoginBuilder loginBuilder;
	@SuppressWarnings("unused")
	private LoginView loginView;
	
	@Override
	public void setLoginBuilder(LoginBuilder loginBuilder) {
		this.loginBuilder = loginBuilder;
	}

	@Override
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

}
