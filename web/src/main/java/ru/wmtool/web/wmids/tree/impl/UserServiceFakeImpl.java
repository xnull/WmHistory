package ru.wmtool.web.wmids.tree.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.security.User;
import ru.wmtool.webmoney.Purse;
import ru.wmtool.webmoney.Wmid;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Фейковый класс, генерирующий user. НЕ ИСПОЛЬЗУЕТСЯ!!
 * @author nazgul
 *
 */
public class UserServiceFakeImpl {
	
	private List<Wmid> wmids;
	private List<WmOperation> operations;
	private List<Purse> purses;
	
	private OperationsHistoryService operationsHistoryService;
	
	public UserServiceFakeImpl(OperationsHistoryService operationsHistoryService) {
		this.operationsHistoryService = operationsHistoryService;
		wmids = generateWmids();
	}
	
	private List<WmOperation> generateOperations(){
		return operationsHistoryService.getOperationsForCurrentUser(null,null,5,30);
	}
	
	private List<Purse> generatePurses(){
		Random random = new Random();
		String[] purseLetter = { "Z", "R", "U", "E", "B", "Y" };
		List<Purse> purses = new ArrayList<Purse>();
		int purseCount  = 4;
		for (int i = 0; i < purseCount; i++) {
			Purse purse = new Purse();
			int moneyType = random.nextInt(6);
			int number = random.nextInt(5000);
			purse.setNumber(purseLetter[moneyType] + String.valueOf(number));
			purse.setOperations(generateOperations());
			purses.add(purse);
		}
		return purses;
	}
	
	private List<Wmid> generateWmids(){
		Random random = new Random();
		List<Wmid> wmids = new ArrayList<Wmid>();
		int wmidCount = 2;
		for (int i = 0; i < wmidCount; i++) {
			Wmid wmid = new Wmid();
			int number = random.nextInt(5000);
			wmid.setNumber(number);
			wmid.setPurses( generatePurses() );
			wmids.add(wmid);
		}
		return wmids;
	}
	
	/**
	 * Генерирует пользователя.
	 * @return пользователь.
	 */
	public User generateUser(){
		User user = new User();
		user.setName("nazgul");
		user.setWmids( wmids );
		return user;
	}
	
	public void setOperationsHistoryService(OperationsHistoryService operationsHistoryService) {
		this.operationsHistoryService = operationsHistoryService;
	}
}
