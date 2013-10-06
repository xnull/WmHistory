package ru.wmtool.dao;

import java.util.List;

import org.apache.log4j.Logger;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.Authority;
import ru.wmtool.security.User;

public class AuthorityDao extends AbstractDao<Authority> {
	
	@LoggerIn
	private static Logger log = Logger.getLogger(AuthorityDao.class);
	
	/**
	 * Сохранить запись авторизации в БД
	 */
	public void save(Authority authority){
		log.debug("Сохраняем запись об авторизации пользователя");
		abstractSave(authority);
	}
	
	public Authority find(Authority authority){
		log.debug("Найти запись Authority в бд");
		return abstractFind(Authority.class, authority);
	}
	
	@SuppressWarnings("unchecked")
	public List<Authority> findByUser(User user){
		log.debug("Найти запись Authority в бд по юзеру");
		return em.createQuery("select a from Authority a where a.user = ?")
				.setParameter(1, user).getResultList();
	}

	public void remove(Authority authority) {
		log.debug("Удалить запись Authority в бд");
		abstractRemove(authority);
	}
}
