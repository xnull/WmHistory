package ru.wmtool.sender.impl;

/***********************************************************************
 * Module:  MailxMessageSender.java
 * Author:  Сервер
 * Purpose: Defines the Class MailxMessageSender
 ***********************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.User;
import ru.wmtool.sender.IMessageSender;

/**
 * Сендер сообщения с помощью *nix-демона mailx.
 * 
 * @author Maxim Angelov
 * 
 * @pdOid 1a98391c-1681-4422-ae3d-3c63b3fb611d
 */
public class MailxMessageSender implements IMessageSender {

	@LoggerIn
	private Logger log;

	/**
	 * @param user
	 * @param message
	 * @pdOid 44e94a03-b5ec-429c-9ebc-6b85fa08314c
	 */
	public void sendMessage(User user, String message) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setSubject("Registration confirmation");
		simpleMessage.setText(message);
		simpleMessage.setTo(user.getContact().getEmail());
		System.out.println("error is here: " + simpleMessage.getTo()[0] + " " + simpleMessage.getSubject());
//		log.info(String.format("Prepare to send email to: '%s', subj: '%s'",
//				simpleMessage.getTo()[0], simpleMessage.getSubject()));
		try {
			Process process = Runtime.getRuntime().exec(
					new String[] { "mailx",
							"-s '" + simpleMessage.getSubject() + "'",
							simpleMessage.getTo()[0] });
			PrintWriter out = new PrintWriter(process.getOutputStream());
			out.println(simpleMessage.getText());
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			log.trace("process output");
			while ((line = in.readLine()) != null) {
				log.trace(line);
			}
		} catch (Exception e) {
			throw new MailSendException("fail", e);
		}
	}
}