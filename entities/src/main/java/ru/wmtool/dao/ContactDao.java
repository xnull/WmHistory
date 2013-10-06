package ru.wmtool.dao;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.Contact;


@Transactional(propagation = Propagation.REQUIRED)
public class ContactDao extends AbstractDao<Contact> {

	@LoggerIn
	private static Logger log;
	
	/**
	 * Поиск контакта с адресом электронной почты
	 * @param email адрес эл. почты
	 * @return Contact - контакт
	 */
	public Contact findByEmail(String email){
		log.debug("Поиск контакта с адресом электронной почты " + email);
		Contact contact = null;
		try{
			contact = abstractFindByField(Contact.class, email, "email");		
		}
		catch(NoResultException e){
			log.info("Контактов с адресом эл. почты " + email + " не найдено!");
		}
		return contact;
	}
}
