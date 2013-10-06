package ru.wmtool.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.wmtool.webmoney.Purse;
/**
 * Класс, для операций с кошельками в БД.
 * @author null (xrw.null@gmail.com)
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class PurseDao extends AbstractDao<Purse> {
	private static final Logger log=Logger.getLogger(PurseDao.class);

	public void save(Purse purse) {
		log.debug("Сохранить в базу данных кошелек " + purse.getNumber());
		abstractSave(purse);
	}

	public List<Purse> findAll(){
		log.info("Поиск всех кошельков в базе данных");
		return abstractFindAll(Purse.class);
	}

	public Purse find(Object primaryKey){
		log.info("Поиск кошелька в базе данных по его primaryKey = " + primaryKey);
		return abstractFind(Purse.class, primaryKey);	
	}

	public Purse findByNumber(String purseNumber){
		log.info("Поиск кошельков " + purseNumber + " в базе данных");
		return abstractFindByField(Purse.class, purseNumber, "number");
	}
}
