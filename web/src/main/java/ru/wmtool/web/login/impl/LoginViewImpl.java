package ru.wmtool.web.login.impl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.web.login.LoginPresenter;
import ru.wmtool.web.login.LoginView;

public class LoginViewImpl extends GenericForwardComposer implements LoginView {
	/**
	 * 
	 *
	 */
	private static final long serialVersionUID = 7943445810127144166L;

	@SuppressWarnings("unused")
	@LoggerIn
	private static Logger log;
	private LoginPresenter loginPresenter;
	

	Textbox pwd1;
	Textbox pwd2;
	Button okBtn;
	Label thelabel;
	Label otherlabel;
	
	@Override
	public LoginPresenter getLoginPresenter() {
		return loginPresenter;
		
	}

	@Override
	public void setLoginPresenter(LoginPresenter loginPresenter) {
		this.loginPresenter = loginPresenter;
	}
}
