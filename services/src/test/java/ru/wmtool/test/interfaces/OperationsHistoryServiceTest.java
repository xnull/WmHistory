package ru.wmtool.test.interfaces;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import ru.wmtool.dao.UserDao;
import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.security.User;
import ru.wmtool.services.OperationHistoryServiceImpl;
import  ru.wmtool.test.interfaces.JpaManager;
import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationsHistoryServiceTest {
	
	
	@Test
	public void getAllOperationsForUserChecking(){
		/**
		 * 1. Получаем из базы юзера - мокаем юзера.
		 * 2. Определяем принадлежит ли данному юзеру, вмид.
		 * 3. Определяем принадлежит ли вмиду кошель.
		 * 4. По кошелю ищем все операции
		 */
		UserDao userDao = new UserDao();
		EntityManager em = JpaManager.getEmf().createEntityManager();
		userDao.setEntityManager(em);
		User testUser = createUser();
		em.getTransaction().begin();
		userDao.save(testUser);
		em.getTransaction().commit();
		
		OperationsHistoryService service = new OperationHistoryServiceImpl(userDao);
						
		List<WmOperation> wmOperations = service.getAllOperationsForUser("TestUser", 123, "R123");
		
		assertEquals(wmOperations.size(), 1);
		assertEquals(wmOperations.get(0).getAmount(), new Double(10));
	}

	
	private User createUser() {
		/* создать юзера вмид кошелек и операции для него */
		User user = new User();
		user.setUsername("TestUser");
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
