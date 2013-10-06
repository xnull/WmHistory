package ru.wmtool.services;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.dao.UserDao;
import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.security.User;
import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Реализация сервиса истории операций.
 * 
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class OperationHistoryServiceImpl implements OperationsHistoryService {
	private static final Logger log = Logger
			.getLogger(OperationHistoryServiceImpl.class);
	private UserDao userDao;

	public OperationHistoryServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<WmOperation> getAllOperationsForUser(String userName,
			Integer wmidNumber, String purseNumber) {
		/**
		 * 1. Получить юзера из базы 2. Проверить содержит ли юзер вмид 3.
		 * Проверить содержит ли вмид кошелек 4. Получить список операций
		 */
		User findedUser = userDao.findByUserName(userName);
		if (!findedUser.containWmid(wmidNumber)) {
			log.warn("User " + userName + "не содержит wmid " + wmidNumber);
			return null;
		}

		Wmid findedWmid = findedUser.getWmidByNumber(wmidNumber);
		if (!findedWmid.containPurse(purseNumber)) {
			log.warn("Wmid " + wmidNumber + "не содержит кошелек "
					+ purseNumber);
			return null;
		}

		Purse findedPurse = findedWmid.getPurseByNumber(purseNumber);

		// Purse findedPurse = new PurseDao().findByNumber(purseNumber);

		return findedPurse.getOperations();
	}

	@Override
	public List<WmOperation> getAllOperationForCurrentUser(Integer wmidNumber,
			String purseNumber) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int getAllOperationsCountForCurrentUser() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, int beginIndex, int countOperations) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, Calendar beginDate, Calendar endDate) {
		// TODO Auto-generated method stub
		return null;
	}
}
