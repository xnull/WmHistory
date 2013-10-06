package ru.wmtool.web.register;

import java.security.NoSuchAlgorithmException;

import ru.wmtool.interfaces.MesssageSendingFailedException;

/**
 * Интерфейс для компонента-презентера, который организовывает взаимодействие 
 * компонента-вью и модели на форме регистрации
 * @author Tanyadem
 *
 */
public interface RegisterPresenter {
	/**
	 * Установить компонент-вью для формы регистрации
	 * @param registerView компонент-вью
	 */
	void setView(RegisterView registerView);

	void setRegisterBuilder(RegisterBuilder registerBuilder);

	/**
	 * Выслать письмо пользователю
	 * @throws NoSuchAlgorithmException 
	 * @throws SameEmailExistException 
	 * @throws MesssageSendingFailedException 
	 */
	void sendLetter(String email) throws NoSuchAlgorithmException, SameEmailExistException, MesssageSendingFailedException;
}
