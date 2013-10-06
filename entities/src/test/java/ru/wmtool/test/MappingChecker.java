package ru.wmtool.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import ru.wmtool.dao.UserDao;
import ru.wmtool.security.User;
import ru.wmtool.test.dbutils.JpaDbUtils;
import ru.wmtool.test.dbutils.JpaManager;
import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * 
 * @author null (xrw.null@gmail.com)
 *
 */
public class MappingChecker {
	private EntityManager em;
	private JpaDbUtils jpaDbUtils; 
	
	@Before
	public void setUp() throws Throwable {
		em = JpaManager.getEmf().createEntityManager();
		jpaDbUtils = new JpaDbUtils(em);
	}

	@Test
	public void checkMappingPurse() throws Throwable {
		jpaDbUtils.clearEntityTable("User");
		
		User user = createUser();				
		UserDao userDao = createUserDao();
		
		em.getTransaction().begin();		
		userDao.save(user);
		em.getTransaction().commit();
		
		List<User> users = userDao.findAll();
		
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).getName(), "TestUser");
		assertEquals(users.get(0).getWmids().get(0).getNumber(), new Integer(123));
		
		Purse purse = users.get(0).getWmids().get(0).getPurses().get(0);
		assertEquals(purse.getNumber(), "R123");
		assertEquals(purse.getOperations().size(), 1);
		assertEquals(purse.getOperations().get(0).getAmount(), new Double(10));
	}

	private UserDao createUserDao() {
		UserDao userDao = new UserDao();
		userDao.setEntityManager(em);
		return userDao;
	}

	private User createUser() {
		/* создать юзера вмид кошелек и операции для него */
		User user = new User();
		user.setName("TestUser");
		user.setWmids(createWmidList());
		return user;
	}

	private List<Wmid> createWmidList() {
		ArrayList<Wmid> wmidList = new ArrayList<Wmid>();
		Wmid wmid1 = new Wmid();
		wmid1.setNumber(123);
		wmidList.add(wmid1);
		wmid1.setPurses(createPurseList());		
		return wmidList;
	}
	
	private ArrayList<Purse> createPurseList() {
		/* создать кошелек с операциями*/
		ArrayList<Purse> purseList = new ArrayList<Purse>();
		Purse purse = new Purse();
		purse.setNumber("R123");
		purse.setOperations(createOperationsList());
		purseList.add(purse);
		return purseList;
	}	

	private ArrayList<WmOperation> createOperationsList() {		
		ArrayList<WmOperation> operations = new ArrayList<WmOperation>();
		WmOperation operation = createOperation();
		operations.add(operation);
		return operations;
	}

	private WmOperation createOperation() {
		WmOperation operation = new WmOperation();
		operation.setAmount(new Double(10));
		operation.setComiss(0.8);
		operation.setOrderid(123);
		return operation;
	}
}
