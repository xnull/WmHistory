package ru.wmtool.web.login;

/**
 * Билдер для страницы входа на портал login.zul
 * @author Tanyadem
 *
 */
public interface LoginBuilder {
	public void setLoginView(LoginView loginView);
	public LoginView getLoginView();
	public void setLoginPresenter(LoginPresenter loginPresenter);
	public LoginPresenter getLoginPresenter();
	
	public void setPasswordInputView(LoginView loginView);
	public PasswordInputView getPasswordInputView();
	public void setPasswordInputPresenter(LoginPresenter loginPresenter);
	public PasswordInputPresenter getPasswordInputPresenter();
}
