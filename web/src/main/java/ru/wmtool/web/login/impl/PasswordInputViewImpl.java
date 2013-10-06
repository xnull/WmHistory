package ru.wmtool.web.login.impl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.generator.UnableSavePasswordException;
import ru.wmtool.security.User;
import ru.wmtool.web.login.PasswordInputPresenter;
import ru.wmtool.web.login.PasswordInputView;

public class PasswordInputViewImpl extends GenericForwardComposer implements
		PasswordInputView {

	/**
	 * 
	 */

	private PasswordInputPresenter passwordInputPresenter;

	private static final long serialVersionUID = 4206901862830485283L;

	@LoggerIn
	private static Logger log;
	private Textbox pwd1;
	private Textbox pwd2;
	@SuppressWarnings("unused")
	private Button submitBtn;
	private User user;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		self.setVisible(false);
		if (requestScope.containsKey("setpassword")) {
			log.debug("Вывод модального окна для принудительной смены пароля");
			self.setVisible(true);
		}
		user = (User) requestScope.get("user");
	}

	/**
	 * Обрабатывает нажатие на кнопку Сохранить пароль
	 * @throws InterruptedException при прерывании действия Messagebox.show
	 */
	public void onClick$submitBtn() throws InterruptedException {
		log.debug("Нажатие на кнопку изменить пароль");
		if (pwd1.getText() != null && pwd1.getText().equals(pwd2.getText())) {
			try {
				passwordInputPresenter.changePassword(user, pwd1.getText());
			} catch (UnableSavePasswordException e) {
				log.error(e);
				Messagebox.show("Ошибка при сохранении пароля!", "Error", Messagebox.OK,
						Messagebox.ERROR);
			}
			self.detach();
		} else {
			log.error("Поля с введенным паролем не совпадают!!!");
			Messagebox.show("Поля с введенным паролем не совпадают!!!", "Error", Messagebox.OK,
					Messagebox.ERROR);

		}
	}

	@Override
	public void setPasswordInputPresenter(
			PasswordInputPresenter passwordInputPresenter) {
		this.passwordInputPresenter = passwordInputPresenter;

	}

	@Override
	public PasswordInputPresenter getPasswordInputPresenter() {
		return passwordInputPresenter;
	}
}
