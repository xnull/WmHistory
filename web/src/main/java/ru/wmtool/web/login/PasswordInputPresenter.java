package ru.wmtool.web.login;

import ru.wmtool.generator.UnableSavePasswordException;
import ru.wmtool.security.User;

/**
 * презентер для модального окна ввода пароля
 * @author Tanyadem
 *
 */
public interface PasswordInputPresenter {
	public void setPasswordInputView(PasswordInputView passwordInputView);
	public void setLoginBuilder(LoginBuilder loginBuilder);
	public void changePassword(User user, String text) throws UnableSavePasswordException;	
}
