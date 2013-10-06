package ru.wmtool.web.register.impl;

import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.web.register.RegisterPresenter;
import ru.wmtool.web.register.RegisterView;
import ru.wmtool.web.register.SameEmailExistException;

public class RegisterViewImpl extends GenericForwardComposer implements
		RegisterView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7217334374858012366L;
	@LoggerIn
	static private Logger log;
	private RegisterPresenter registerPresenter;

	Button sendBtn;
	Textbox emailBox;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (requestScope.get("activation") != null
				&& requestScope.get("activation").equals("false")) {
			Messagebox
					.show("Возможно, ваш код активации истек, попробуйте зарегистрироваться еще раз",
							"Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Override
	public RegisterPresenter getRegisterPresenter() {
		return registerPresenter;
	}

	@Override
	public void setRegisterPresenter(RegisterPresenter registerPresenter) {
		this.registerPresenter = registerPresenter;
	}

	/**
	 * Обработка события нажатия на кнопку Отправить письмо (письмо активации)
	 * 
	 * @throws WrongValueException
	 * @throws NoSuchAlgorithmException
	 * @throws InterruptedException
	 * @throws SameEmailExistException
	 */
	public void onClick$sendBtn() throws WrongValueException, InterruptedException {
		log.debug("Активация события нажатия на кнопку отправки письма активации пользователю");
		try {
			registerPresenter.sendLetter(emailBox.getText());
			Messagebox
					.show("Проверьте свою электронную почту! Письмо с кодом активации уже выслано",
							"Information", Messagebox.OK,
							Messagebox.INFORMATION);
			// emailBox.setText("");
		} catch (NoSuchAlgorithmException e) {
			Messagebox
					.show("Проблемы с механизмом формирования активации. Алгоритма формирования кода не обнаружено",
							"Error", Messagebox.OK, Messagebox.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK,
					Messagebox.ERROR);
			e.printStackTrace();
		}
	}

}
