package ru.wmtool.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.security.User;
import ru.wmtool.webmoney.Wmid;
/**
 * Класс, для операций с кошельками в БД.
 * @author null (xrw.null@gmail.com)
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class WmidDao extends AbstractDao<Wmid> {
	private static final Logger log=Logger.getLogger(WmidDao.class);

	public void save(Wmid wmid) {
		abstractSave(wmid);
	}

	public List<Wmid> findAll() {
		log.info("Поиск все wmid-ы в базе данных");
		return abstractFindAll(Wmid.class);
	}

	public Wmid find(Object primaryKey) {
		log.info("Поиск wmid в базе данных по его primaryKey = " + primaryKey);
		return abstractFind(Wmid.class, primaryKey);	
	}
	
	public Wmid findByNumber(Integer wmidNumber){
		log.info("Поиск wmid " + wmidNumber + " в базе данных");
		return abstractFindByField(Wmid.class, wmidNumber, "number");
	}

	@SuppressWarnings("unchecked")
	public List<Wmid> findByUser(User user) {
		log.info("Поиск wmid по юзеру " + user);
		return em.createQuery("select w from Wmid w where w.user = ?")
				.setParameter(1, user).getResultList();
	}
}
