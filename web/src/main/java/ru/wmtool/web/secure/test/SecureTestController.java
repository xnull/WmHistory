package ru.wmtool.web.secure.test;

import org.springframework.security.core.Authentication;
import org.zkoss.spring.security.SecurityUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.api.Label;

public class SecureTestController extends GenericForwardComposer {
	private static final long serialVersionUID = 1259186798156343359L;
	
	private Label userNameLabel;
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Authentication auth = SecurityUtil.getAuthentication();
		userNameLabel.setValue(String.format("Hello, %s - %s", auth.getName(), auth.getPrincipal()));
		// test
	}

}
