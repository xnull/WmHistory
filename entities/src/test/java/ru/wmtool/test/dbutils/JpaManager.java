package ru.wmtool.test.dbutils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Класс для инициализации и получения EntityManagerFactory и т.д. всё что относится
 * к jpa 
 * @author null (xrw.null@gmail.com)
 *
 */
public class JpaManager {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("WmHistory");
		
	public static EntityManagerFactory getEmf(){
		return emf;
	}
}
