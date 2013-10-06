package ru.wmtool.test.dbutils;

import javax.persistence.EntityManager;

/**
 * Утилиты для работы с базой данных. Для очистки, заполнения тестовыми данными
 * и т.д.
 * 
 * @author null
 * 
 */
public class JpaDbUtils {	
	private EntityManager em;
	
	public JpaDbUtils(EntityManager em) {
		this.em = em;
	}

	/**
	 * Очистить таблицу(ы) в которой хранятся сущности
	 * 
	 * @param entityName
	 * @throws Throwable
	 */
	public void clearEntityTable(String entityName) throws Throwable {
		em.getTransaction().begin();
		em.createQuery(String.format("DELETE FROM %s entity", entityName)).executeUpdate();
		em.getTransaction().commit();
	}
}
