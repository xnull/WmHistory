package ru.wmtool.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
/**
 * Класс, для операций с пользователями в БД.
 * @author null (xrw.null@gmail.com)
 */
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao extends AbstractDao<User> {
	@LoggerIn
	private static Logger log;

	public void save(User user) {
		log.debug("Сохранение юзера");
		abstractSave(user);
	}
	
	public void remove(User user){
		log.debug("Удаление юзера");
		abstractRemove(user);
	}

	public List<User> findAll() throws Throwable {
		log.debug("Поиск всех юзеров");
		return abstractFindAll(User.class);
	}

	public User find(Object primaryKey){
		log.debug("Поиск юзера");
		return abstractFind(User.class, primaryKey);	
	}

	public User findByUserName(String userName){
		log.debug("Поиск юзера " + userName);
		return abstractFindByField(User.class, userName, "name");		
	}

	/**
	 * Поиск юзера по коду активации
	 * @param registrationConfirmCode запись кода активации
	 * @return запись юзера
	 * @throws EmptyResultException нет юзеров с данным кодом активации
	 * @throws NotSingleResultException юзеров с данным кодом активации больше одного!
	 */
	public User findByRegistrationConfirmCode(RegistrationConfirmCode registrationConfirmCode) throws EmptyResultException, NotSingleResultException {
		log.debug("Поиск юзера по коду активации");
		@SuppressWarnings("unchecked")
		List<User> users =  (List<User>) em.createQuery("select u from User u where u.registrationConfirmCode.confirmCode = ?").setParameter(1, registrationConfirmCode.getConfirmCode()).getResultList();
		if (users.isEmpty()){
			throw new EmptyResultException(User.class);
		}
		else if(users.size()>1){
			throw new NotSingleResultException(users, User.class);
		}
		else{
			User user = users.get(0);
			em.detach(user);
			return user;
		}
	}

	/**Поиск юзера по адресу электронной почты, который прописан в контакте пользователя
	 * @param email адрес электронной почты
	 * @return запись пользователя
	 * @throws EmptyResultException 
	 * @throws NotSingleResultException 
	 */
	public User findByEmail(String email) throws EmptyResultException, NotSingleResultException {
		log.debug("Поиск юзера по адресу эл. почты");
		@SuppressWarnings("unchecked")
		List<User> users =  (List<User>) em.createQuery("select u from User u where u.contact.email = ?").setParameter(1, email).getResultList();
		if (users.isEmpty()){
			throw new EmptyResultException(User.class);
		}
		else if(users.size()>1){
			throw new NotSingleResultException(users, User.class);
		}
		else{
			return users.get(0);
		}
	}

	public void update(User user) {
		em.merge(user);		
	}
	
	public void detach(User user){
		em.detach(user);
	}
	
	public void refresh(User user){
		em.refresh(user);
	}
}
