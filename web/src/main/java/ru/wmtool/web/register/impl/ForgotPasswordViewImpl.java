package ru.wmtool.web.register.impl;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.web.register.ForgotPasswordPresenter;
import ru.wmtool.web.register.ForgotPasswordView;
import ru.wmtool.web.register.SameEmailExistException;

public class ForgotPasswordViewImpl extends GenericForwardComposer implements
		ForgotPasswordView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 305433159306137736L;

	@LoggerIn
	static private Logger log;
	private ForgotPasswordPresenter forgotPasswordPresenter;

	Button sendBtn;
	Textbox emailBox;

	@Override
	public ForgotPasswordPresenter getForgotPasswordPresenter() {
		return forgotPasswordPresenter;
	}

	@Override
	public void setForgotPasswordPresenter(ForgotPasswordPresenter forgotPasswordPresenter) {
		this.forgotPasswordPresenter = forgotPasswordPresenter;
	}

	/**
	 * Обработка события нажатия на кнопку Отправить письмо (письмо сброса пароля)
	 * 
	 * @throws WrongValueException
	 * @throws NoSuchAlgorithmException
	 * @throws InterruptedException
	 * @throws SameEmailExistException
	 */
	public void onClick$sendBtn() throws WrongValueException, InterruptedException {
		log.debug("Активация события нажатия на кнопку отправки письма сброса пароля пользователю");
			try {
				forgotPasswordPresenter.sendLetter(emailBox.getText());
				Messagebox
						.show("Проверьте свою электронную почту! Письмо со ссылкой на страницу сброса пароля уже выслано",
								"Information", Messagebox.OK,
								Messagebox.INFORMATION);
				// emailBox.setText("");
			} catch (Exception e) {
				Messagebox.show(e.getMessage(), "Error", Messagebox.OK,
						Messagebox.ERROR);
				log.error(e);
			} 
	}

}
