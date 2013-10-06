package ru.wmtool.test.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.dao.RegistrationConfirmationCodeDao;
import ru.wmtool.dao.UserDao;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.test.dbutils.JpaDbUtils;
import ru.wmtool.test.dbutils.JpaManager;

public class RegistrationConfirmationCodeDaoTest {
	private EntityManager em;

	@BeforeClass
	public static void setUpTestCase() throws Exception {
		DOMConfigurator.configure(RegistrationConfirmationCodeDaoTest.class
				.getResource("log4j.xml"));
	}

	@Before
	public void setUp() throws Throwable {
		em = JpaManager.getEmf().createEntityManager();
		JpaDbUtils jpaDbUtils = new JpaDbUtils(em);
		jpaDbUtils.clearEntityTable(RegistrationConfirmCode.class.getName());
	}

	@Test
	public void RegistrationConfirmCodePersistProcess() throws Exception {

		final String confirmCodeStr = Integer.toHexString(97835423);
		final String nativeQuery = "select count(*) from registration_confirm_code "
				+ "where id = :id "
				+ "and user = :user_id "
				+ "and confirm_code = :confirm_code";

		UserDao userDao = new UserDao();
		userDao.setEntityManager(em);

		RegistrationConfirmationCodeDao codeDao = new RegistrationConfirmationCodeDao();
		codeDao.setEntityManager(em);

		EntityTransaction trx = em.getTransaction();
		trx.begin();

		User newUser = new User();
		newUser.setName("Evgin Gocharov");
		newUser.setUsername("egoncharov");
		userDao.save(newUser);

		RegistrationConfirmCode confirmCode = new RegistrationConfirmCode(newUser, confirmCodeStr, new Timestamp((new Date()).getTime()));
		codeDao.saveConfirmCode(confirmCode);
		trx.commit();
		trx = em.getTransaction();
		trx.begin();
		Query query = em.createNativeQuery(nativeQuery)
				.setParameter("id", confirmCode.getId())
				.setParameter("user_id", newUser.getUsername())
				.setParameter("confirm_code", confirmCodeStr);
		Object resObj = query.getSingleResult();

		assertNotNull(resObj);
		assertTrue(resObj instanceof BigInteger);
		System.out.println("resObj " + resObj);
		assertTrue(BigInteger.ONE.equals((BigInteger) resObj));

		trx.commit();
	}
	
	@Test
	public void findByRegistrationConfirmCodeAnd24hoursTest(){
		RegistrationConfirmationCodeDao codeDao = new RegistrationConfirmationCodeDao();
		codeDao.setEntityManager(em);

		UserDao userDao = new UserDao();
		userDao.setEntityManager(em);

		String confirmCodeStr = Integer.toHexString(97835423);
		
		EntityTransaction trx = em.getTransaction();
		trx.begin();

		User newUser = new User();
		newUser.setName("Tanya Dembelova");
		newUser.setUsername("TDembelova");
		userDao.save(newUser);

		RegistrationConfirmCode confirmCode = new RegistrationConfirmCode(newUser, confirmCodeStr, new Timestamp((new Date()).getTime()));
		confirmCode.setId(1);
		codeDao.saveConfirmCode(confirmCode);

		trx.commit();
		assertNotNull(confirmCode);
		System.out.println(confirmCode);
		RegistrationConfirmCode confirmCode2 = null;
		trx = em.getTransaction();
		trx.begin();
		try {
			confirmCode2 = codeDao.findByRegistrationConfirmCodeAnd24hours(confirmCodeStr);
			trx.commit();
		} catch (NotSingleResultException e) {
			e.printStackTrace();
			trx.rollback();
		}
		assertNotNull(confirmCode2);
	}
}
