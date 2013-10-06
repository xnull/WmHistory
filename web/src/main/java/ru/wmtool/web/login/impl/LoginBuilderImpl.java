package ru.wmtool.web.login.impl;

import ru.wmtool.web.login.LoginBuilder;
import ru.wmtool.web.login.LoginPresenter;
import ru.wmtool.web.login.LoginView;
import ru.wmtool.web.login.PasswordInputPresenter;
import ru.wmtool.web.login.PasswordInputView;

public class LoginBuilderImpl implements LoginBuilder {

	private LoginPresenter loginPresenter;
	private LoginView loginView;
	private PasswordInputPresenter passwordInputPresenter;
	private PasswordInputView passwordInputView;
	
	private LoginBuilderImpl() {
	}
	
	public static LoginBuilder createNew(LoginPresenter loginPresenter, LoginView loginView,
			PasswordInputPresenter passwordInputPresenter,
			PasswordInputView passwordInputView) {
		LoginBuilder loginBuilder = new LoginBuilderImpl();
		loginBuilder.setLoginPresenter(loginPresenter);
		loginBuilder.setLoginView(loginView);
		loginBuilder.setPasswordInputPresenter(loginPresenter);
		loginBuilder.setPasswordInputView(loginView);
		return loginBuilder;
	}



	@Override
	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public LoginView getLoginView() {
		return loginView;
	}

	@Override
	public void setLoginPresenter(LoginPresenter loginPresenter) {
		this.loginPresenter = loginPresenter;
	}

	@Override
	public LoginPresenter getLoginPresenter() {
		return loginPresenter;
	}

	@Override
	public void setPasswordInputView(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public PasswordInputView getPasswordInputView() {
		return passwordInputView;
	}

	@Override
	public void setPasswordInputPresenter(LoginPresenter loginPresenter) {
		this.loginPresenter = loginPresenter;
	}

	@Override
	public PasswordInputPresenter getPasswordInputPresenter() {
		return passwordInputPresenter;
	}

}
