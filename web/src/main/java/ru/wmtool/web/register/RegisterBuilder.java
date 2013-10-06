package ru.wmtool.web.register;

public interface RegisterBuilder {

	public void setRegisterView(RegisterView registerView);

	public RegisterView getRegisterView();

	public void setRegisterPresenter(RegisterPresenter registerPresenter);

	public RegisterPresenter getRegisterPresenter();

	public void setForgotPasswordView(ForgotPasswordView forgotPasswordView);

	public ForgotPasswordView getForgotPasswordView();

	public void setForgotPasswordPresenter(ForgotPasswordPresenter forgotPasswordPresenter);

	public ForgotPasswordPresenter getForgotPasswordPresenter();

}
