package ru.wmtool.web.register.impl;

import ru.wmtool.web.register.ForgotPasswordPresenter;
import ru.wmtool.web.register.ForgotPasswordView;
import ru.wmtool.web.register.RegisterBuilder;
import ru.wmtool.web.register.RegisterPresenter;
import ru.wmtool.web.register.RegisterView;

public class RegisterBuilderImpl implements RegisterBuilder {

	private RegisterPresenter registerPresenter;
	private RegisterView registerView;
	private ForgotPasswordPresenter forgotPasswordPresenter;
	private ForgotPasswordView forgotPasswordView;

	private RegisterBuilderImpl() {
	}

	static RegisterBuilderImpl createNew(RegisterPresenter registerPresenter,
			RegisterView registerView, ForgotPasswordPresenter forgotPasswordPresenter,
			ForgotPasswordView forgotPasswordView) {
		RegisterBuilderImpl builder = new RegisterBuilderImpl();
		builder.setRegisterPresenter(registerPresenter);
		builder.setRegisterView(registerView);
		builder.setForgotPasswordPresenter(forgotPasswordPresenter);
		builder.setForgotPasswordView(forgotPasswordView);
		return builder;
	}

	@Override
	public RegisterPresenter getRegisterPresenter() {
		return registerPresenter;
	}

	@Override
	public void setRegisterPresenter(RegisterPresenter registerPresenter) {
		this.registerPresenter = registerPresenter;
	}

	@Override
	public RegisterView getRegisterView() {
		return registerView;
	}

	@Override
	public void setRegisterView(RegisterView registerView) {
		this.registerView = registerView;
	}

	@Override
	public void setForgotPasswordView(ForgotPasswordView forgotPasswordView) {
		this.forgotPasswordView = forgotPasswordView;	
	}

	@Override
	public ForgotPasswordView getForgotPasswordView() {
		return forgotPasswordView;
	}

	@Override
	public void setForgotPasswordPresenter(
			ForgotPasswordPresenter forgotPasswordPresenter) {
		this.forgotPasswordPresenter = forgotPasswordPresenter;
	}

	@Override
	public ForgotPasswordPresenter getForgotPasswordPresenter() {
		return forgotPasswordPresenter;
	}

}
