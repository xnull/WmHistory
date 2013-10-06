package ru.wmtool.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Абстрактный класс для персистентности
 * Класс для работы с базой.
 * Dao - data access object (объект для достпа к данным).
 * @author null (xrw.null@gmail.com)
 * @param <T>
 * 
 */
public abstract class AbstractDao<T> {
	private static final Logger log = LogManager.getLogger(AbstractDao.class);
	
	@PersistenceContext
	protected EntityManager em;
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Сохранение сущности в базу данных
	 * @param entity
	 */
	protected void abstractSave(T entity) {
		log.debug("Сохраняем сущность " + entity.getClass().getName());
		em.persist(entity);
	}
	
	/**
	 * Удаление сущности из базы данных
	 * @param entity
	 */
	protected void abstractRemove(T entity) {
		log.debug("Удаляем сущность" + entity.getClass().getName());
		em.remove(entity);
	}

	/**
	 * Поиск сущности в базе по её id
	 * @param entityClass тип сущности
	 * @param primaryKey идентификатор в базе
	 * @return
	 */
	protected T abstractFind(Class<T> entityClass, Object primaryKey){
		log.debug("entityclass: " + entityClass.getName());
		log.debug("primaryKey: " + primaryKey);
//		log.debug(String.format("Ищем сущность: %s, id: %s" + entityClass.getName(), primaryKey.toString()));
		T findedEntity = em.find(entityClass, primaryKey);
		return findedEntity;
	}

	/**
	 * Найти все сущности в базе данных
	 * @param entity тип сущности
	 * @return
	 */
	protected List<T> abstractFindAll(Class<T> entity)  {
		log.debug("Ищем все сущности: " + entity.getName());
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
		Root<T> from = criteriaQuery.from(entity);
		criteriaQuery.select(from);
		TypedQuery<T> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	/**
	 * Поиск сущности по конкретному значению одного из его полей.
	 * Фактически это sql запрос с условием where:
	 * SELECT * FROM Entity WHERE (number=123)
	 * 
	 * @param entity тип сущности
	 * @param findedValue значение поля сущности по которому мы его ищем в базе  (в выше приведенном примере это 123)
	 * @param fieldName имя поля в базе к каотором прменяем условие (в выше приведенном примере это number)
	 * @return
	 */
	protected T abstractFindByField(Class<T> entity, Object findedValue, String fieldName){
		log.debug(String.format("Ищем сущность по полю: %s, значение поля: %s", fieldName, findedValue));
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entity);
		Root<T> from = criteriaQuery.from(entity);
		criteriaQuery.select(from).where(criteriaBuilder.equal(from.get(fieldName), findedValue));
		
		TypedQuery<T> query = em.createQuery(criteriaQuery);
		return query.getSingleResult();
	}
}
