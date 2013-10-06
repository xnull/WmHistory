package ru.wmtool.message.registration.impl;

/***********************************************************************
 * Module:  RegistrationConfirmMessageBuilder.java
 * Author:  Maxim
 * Purpose: Defines the Class RegistrationConfirmMessageBuilder
 ***********************************************************************/

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.message.registration.RegistrationConfirmMessageBuilder;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.templates.engine.TemplateEngine;

/**
 * 
 * @author Maxim Angelov
 * 
 * @pdOid a769d915-bd3e-4582-95a1-a5c146311e9e
 */

public class RegistrationConfirmMessageBuilderImpl implements
		RegistrationConfirmMessageBuilder {

	private static String TEMPLATE = "registrationConfirmationMail.ftl";
	private static String USER_KEY = "user";
	private static String CODE_KEY = "code";
	private static String TYPE_KEY = "type";
	private static String ACTIVATION_SERVLET_PATH_KEY = "activationServletPath";

	private String activationServletPath;
	
	@SuppressWarnings("unused")
	@LoggerIn
	private Logger log;

	/** @pdOid 0411071b-b64e-4ee0-a80a-40ce0a5996f6 */
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * @param user
	 * @param code
	 * @throws Exception
	 * @pdOid b2eb3af9-907e-43c6-a6e8-c93fbd3fcb41
	 */
	public String buildMessage(User user, RegistrationConfirmCode code)
			throws Exception {
		Map<String, Object> modelData = new HashMap<String, Object>();		
		modelData.put(CODE_KEY, code);
		modelData.put(TYPE_KEY, "r");
		modelData.put(ACTIVATION_SERVLET_PATH_KEY, activationServletPath);

		String message = templateEngine.render(TEMPLATE, modelData);
		return message;
	}
	
	public void setActivationServletPath(String activationServletPath) {
		this.activationServletPath = activationServletPath;
	}
}