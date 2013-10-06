package ru.wmtool.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import ru.wmtool.dao.WmidDao;
import ru.wmtool.test.dbutils.JpaDbUtils;
import ru.wmtool.test.dbutils.JpaManager;
import ru.wmtool.webmoney.Wmid;

public class WmidDaoTest {
	private EntityManager em;
	private JpaDbUtils jpaDbUtils; 
	
	@Before
	public void setUp() throws Throwable {
		em = JpaManager.getEmf().createEntityManager();
		jpaDbUtils = new JpaDbUtils(em);
	}
	
	@Test
	public void checkFindWmidByNumber() throws Throwable {
		jpaDbUtils.clearEntityTable("Wmid");
		WmidDao wmidDao = createWmidDao();		

		em.getTransaction().begin();
		wmidDao.save(createWmid());		
		em.getTransaction().commit();
		
		Wmid findedWmid = wmidDao.findByNumber(123);
		assertEquals(findedWmid.getNumber(), new Integer(123));
	}

	private WmidDao createWmidDao() {
		WmidDao wmidDao = new WmidDao();
		wmidDao.setEntityManager(em);
		return wmidDao;
	}

	private Wmid createWmid() {
		Wmid wmid = new Wmid();
		wmid.setNumber(123);
		return wmid;
	}
}
