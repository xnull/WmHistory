package ru.wmtool.generator.impl;

import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.commons.utils.CodeUtils;
import ru.wmtool.dao.AuthorityDao;
import ru.wmtool.dao.ContactDao;
import ru.wmtool.dao.EmptyResultException;
import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.dao.RegistrationConfirmationCodeDao;
import ru.wmtool.dao.UserDao;
import ru.wmtool.generator.ConfirmCodeAlreadyUsedException;
import ru.wmtool.generator.NoRegistrationConfirmCodeIn24HoursFoundException;
import ru.wmtool.generator.UnableSavePasswordException;
import ru.wmtool.generator.UserService;
import ru.wmtool.security.Authority;
import ru.wmtool.security.Contact;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;

/**
 * Реализация сервиса генерации учетных записей пользователей
 * 
 * @author Tanyadem
 * 
 */
@Transactional
public class UserServiceImpl implements UserService {

	@LoggerIn
	private static Logger log;

	@Autowired
	ContactDao contactDao;
	@Autowired
	UserDao userDao;
	@Autowired
	RegistrationConfirmationCodeDao registrationCodeDao;
	@Autowired
	AuthorityDao authorityDao;

	// Для проверки в junit
	 public UserServiceImpl() {
	 }
	
	
	
	 public UserServiceImpl(UserDao userDao, RegistrationConfirmationCodeDao
	 confirmationCodeDao,
	 ContactDao contactDao, AuthorityDao authorityDao) {
	 this.userDao = userDao;
	 this.contactDao = contactDao;
	 this.registrationCodeDao = confirmationCodeDao;
	 this.authorityDao = authorityDao;
	 }

	/**
	 * Создание пользователя без сохранения в БД
	 * 
	 * @return
	 */
	private User createUser() {
		log.debug("Создание нового пустого пользователя");
		User user = new User();
		Contact contact = new Contact();
		user.setContact(contact);
		user.setEnabled(false);
		return user;
	}

	/**
	 * Создает нового пользователя User, связанный Contact с полученным
	 * email'ом, генерирует пароль из email'а. По умолчанию, юзеру присваивается
	 * роль ROLE_USER. Затем сохраняет запись User, Contact и Authority в БД
	 * 
	 */
	@Override
	public User createAndSaveUser(String email) {
		if (email == null) {
			throw new IllegalArgumentException(
					"Параметр email не может быть null");
		}
		log.info("Создание нового пользователя с адресом эл. почты " + email);
		User user = createUser();
		String lowerCaseEmail = email.toLowerCase();
		user.getContact().setEmail(lowerCaseEmail);
		user.setUsername(lowerCaseEmail);
		try {
			user.setPassword(CodeUtils.generateSHA256(lowerCaseEmail));
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
		saveUser(user);
		Authority authority = new Authority(user, "ROLE_USER");
		authorityDao.save(authority);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.wmtool.generator.UserGenerationService#saveUser(ru.wmtool.security
	 * .User)
	 */
	@Override
	public User saveUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException(
					"Параметр user не может быть null");
		}
		log.info("Сохранение в БД пользователя: " + user);
		userDao.save(user);
		return user;
	}

	@Override
	public Contact getContactByEmail(String email) {
		log.info("Получение из БД записи контакта с email: " + email);
		if (email == null) {
			throw new IllegalArgumentException(
					"Параметр email не может быть null");
		}
		return contactDao.findByEmail(email.toLowerCase());
	}

	@Override
	public User activateUser(String code) throws NotSingleResultException,
			EmptyResultException,
			NoRegistrationConfirmCodeIn24HoursFoundException,
			ConfirmCodeAlreadyUsedException {
		log.info("Активация юзера по коду " + code);
		if (code == null) {
			throw new IllegalArgumentException(
					"Параметр code не может быть null");
		}
		RegistrationConfirmCode confirmCode = registrationCodeDao
				.findByRegistrationConfirmCodeAnd24hours(code);
		if (confirmCode == null) {
			throw new NoRegistrationConfirmCodeIn24HoursFoundException(
					confirmCode);
		}
		if (confirmCode.getUsed()) {
			throw new ConfirmCodeAlreadyUsedException("Код активации не действителен :-(", confirmCode);
		}
		confirmCode.setUsed(true);
		return userDao.findByRegistrationConfirmCode(confirmCode);
	}

	@Override
	public User getUserByEmail(String email) throws EmptyResultException,
			NotSingleResultException {
		log.info("Получение записи юзера по адресу электронной почты " + email);
		if (email == null) {
			throw new IllegalArgumentException(
					"Параметр email не может быть null");
		}
		return userDao.findByEmail(email.toLowerCase());
	}

	@Override
	public void savePassword(User user, String password)
			throws UnableSavePasswordException {
		log.info("Сохранение пароля для юзера " + user);
		if (user == null) {
			throw new IllegalArgumentException(
					"Параметр user не может быть null");
		}
		if (password == null) {
			throw new IllegalArgumentException(
					"Параметр password не может быть null");
		}
		User user2 = userDao.find(user.getUsername());
		try {
			user2.setPassword(CodeUtils.generateSHA256(password));
		} catch (NoSuchAlgorithmException e) {
			throw new UnableSavePasswordException(
					"Не удается сохранить пароль в БД", e);
		}
		user2.setEnabled(true);
	}
}
