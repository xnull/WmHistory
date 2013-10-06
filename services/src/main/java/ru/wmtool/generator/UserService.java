package ru.wmtool.generator;

import java.security.NoSuchAlgorithmException;

import ru.wmtool.dao.EmptyResultException;
import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.security.Contact;
import ru.wmtool.security.User;

/**
 * Сервис для генерации учетных записей пользователей
 * 
 * @author Tanyadem
 * 
 */
public interface UserService {


	/**
	 * Сохранить пользователя в БД
	 * 
	 * @param user
	 * @return
	 */
	public User saveUser(User user);

	
	/**
	 * Создает и сохраняет нового пользователя User
	 * 
	 * @param email
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	User createAndSaveUser(String email);

	/**
	 * Возвращает запись контакта с данным email'ом
	 * 
	 * @param email
	 * @return контакт пользователя. Если такой не найден, то null
	 */
	Contact getContactByEmail(String email);

	/**
	 * Проводит активацию учетной записи пользователя. Если активационный код
	 * найден - пользователь помечается как enabled.
	 * 
	 * @param registrationConfirmCode
	 *            код активации
	 * @return 
	 * @throws NotSingleResultException
	 *             при поиске кода активации либо юзера обнаружено более 1
	 *             записи
	 * @throws EmptyResultException
	 *             при поиске кода активации не обнаружено ни одной записи
	 * @throws NoRegistrationConfirmCodeIn24HoursFoundException
	 *             не обнаружено кода активации в БД
	 * @throws ConfirmCodeAlreadyUsedException 
	 */
	public User activateUser(String registrationConfirmCode)
			throws NotSingleResultException, EmptyResultException,
			NoRegistrationConfirmCodeIn24HoursFoundException, ConfirmCodeAlreadyUsedException;

	/**Возвращает запись юзера с данным email
	 * @param email
	 * @return
	 * @throws NotSingleResultException 
	 * @throws EmptyResultException 
	 */
	public User getUserByEmail(String email) throws EmptyResultException, NotSingleResultException;

	/**
	 * Сохраняет введенный пароль в БД
	 * @param object
	 * @param text
	 * @throws UnableSavePasswordException 
	 */
	void savePassword(User user, String text) throws UnableSavePasswordException;
}
