package ru.wmtool.interfaces;

import java.util.Calendar;
import java.util.List;

import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Севрис для доступа к операциям по истории операций.
 * 
 * @author Evgin
 * 
 */
public interface OperationsHistoryService {

	/**
	 * Получить все операции по кошельку принадлежащему юзеру.
	 * 
	 * @param user
	 * @return
	 */
	public List<WmOperation> getAllOperationsForUser(String userName,
			Integer wmidNumber, String purseNumber);

	/**
	 * Получить все операции по кошельку для текущего пользователь.<br/>
	 * <i>Определение текущего пользователя происходит на основе данных об
	 * авторизованном пользователе, текущий поток.</i>
	 * 
	 * @return
	 */
	public List<WmOperation> getAllOperationForCurrentUser(Integer wmidNumber,
			String purseNumber);

	/**
	 * Получить количество всех операции для текущего пользователь.<br/>
	 * <i>Определение текущего пользователя происходит на основе данных об
	 * авторизованном пользователе, текущий поток.</i>
	 * 
	 * @return
	 */
	public int getAllOperationsCountForCurrentUser();

	/**
	 * Получить операции для текущего пользователя - начиная с beginIndex в
	 * количестве countOperations.
	 * 
	 * @param beginIndex
	 *            начальный индекс
	 * @param countOperations
	 *            количество операций
	 * 
	 * @return
	 */
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, int beginIndex, int countOperations);

	/**
	 * Получить операции для текущего пользователя - начиная с начальной даты,
	 * заканчивая конечной датой.
	 * 
	 * @param wmidNumber
	 *            номер вмида.
	 * @param purseNumber
	 *            номер кошелька.
	 * @param beginDate
	 *            начальная дата.
	 * @param endDate
	 *            конечная дата.
	 * @return список операций.
	 */
	public List<WmOperation> getOperationsForCurrentUser(Integer wmidNumber,
			String purseNumber, Calendar beginDate, Calendar endDate);

}
