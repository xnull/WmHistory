package ru.wmtool.dao;

/***********************************************************************
 * Module:  ConfirmationCodeDao.java
 * Author:  Maxim
 * Purpose: Defines the Class ConfirmationCodeDao
 ***********************************************************************/

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.commons.logger.LoggerIn;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;

/**
 * DAO для данных подтверждения регистрации.
 * 
 * @pdOid 294dee51-744b-485c-9b77-4a892b40c3ce
 */
@Repository
// Spring будет создавать этот класс во время запросов пользователя
@Transactional(propagation = Propagation.REQUIRED)
// транзация
public class RegistrationConfirmationCodeDao extends
		AbstractDao<RegistrationConfirmCode> {

	 @LoggerIn
	private Logger log; //= Logger.getLogger(RegistrationConfirmationCodeDao.class);

	/**
	 * @param confirmCode
	 * @pdOid 2f86fd1f-79fd-41fa-b245-45ea251fc331
	 */
	public void saveConfirmCode(RegistrationConfirmCode confirmCode) {
		log.debug("Сохранение кода подтверждения: " + confirmCode);
		em.persist(em.merge(confirmCode));
	}

	public RegistrationConfirmCode findByRegistrationConfirmCodeAnd24hours(
			String registrationConfirmCode) throws NotSingleResultException {
		log.debug("Поиск записи кода активации по коду и укладывающегося по сроку в 24часа с даты регистрации на портале");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, -24);
		Query query = em
				.createQuery(
						"select c from RegistrationConfirmCode c "
								+ "where confirmCode = :confirmCode "
								+ "and registrationDate >= :registrationDate")
				.setParameter("confirmCode", registrationConfirmCode)
				.setParameter("registrationDate", cal.getTime());
		@SuppressWarnings("unchecked")
		List<RegistrationConfirmCode> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			log.warn("Код активации не содержится в БД, либо срок действия истек");
			return null;
		} else if (resultList.size() > 1) {
			throw new NotSingleResultException(resultList,
					RegistrationConfirmCode.class);
		} else {
			return resultList.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public List<RegistrationConfirmCode> findByUser(User user) {
		return em.createQuery("select r from RegistrationConfirmCode r where r.user = ?")
		.setParameter(1, user).getResultList();
	}

}