package ru.wmtool.web.register;

import java.security.NoSuchAlgorithmException;

import ru.wmtool.dao.EmptyResultException;
import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.interfaces.MesssageSendingFailedException;

/**
 * Интерфейс для компонента-презентера, который организовывает взаимодействие 
 * компонента-вью и модели на форме Забыли пароль?
 * @author Tanyadem
 *
 */
public interface ForgotPasswordPresenter {
	/**
	 * Установить компонент-вью для формы регистрации
	 * @param registerView компонент-вью
	 */
	void setView(ForgotPasswordView forgotPasswordView);

	void setRegisterBuilder(RegisterBuilder registerBuilder);

	/**
	 * Выслать письмо пользователю
	 * @throws NoSuchAlgorithmException 
	 * @throws SameEmailExistException 
	 * @throws NotSingleResultException 
	 * @throws EmptyResultException 
	 * @throws MesssageSendingFailedException 
	 */
	void sendLetter(String email) throws SameEmailExistException, EmptyResultException, NotSingleResultException, MesssageSendingFailedException;
}
