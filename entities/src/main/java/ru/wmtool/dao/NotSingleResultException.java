package ru.wmtool.dao;

import java.util.List;

public class NotSingleResultException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2419078514157336739L;
	
	private List<?> list;
	private Class<?> clazz;

	public NotSingleResultException() {
		super();
	}
	
	/**
	 * Исключение, вызываемое при запросе, когда ожидается единственный результат
	 * @param message сообщение об ошибке
	 * @param list список сущностей, полученный при запросе
	 * @param clazz класс сущности
	 */
	public <T,U> NotSingleResultException(String message, List<T> list, Class<U> clazz){
		super(message);
		this.list = list;
		this.clazz = clazz;
	}
	
	/**Исключение, вызываемое при запросе, когда ожидается единственный результат
	 * @param list результат запроса - список List
	 * @param clazz класс сущности
	 */
	public <T,U> NotSingleResultException(List<T> list, Class<U> clazz) {
		super("Было найдено более 1 записи сущности " + clazz);
		this.list = list;
		this.clazz = clazz;
	}
	
	/**
	 * Получить результат запроса
	 * @return результат запроса - список List
	 */
	public List<?> getList() {
		return list;
	}
	
	/**
	 * Получить класс сущности запроса
	 * @return класс
	 */
	public Class<?> getClazz() {
		return clazz;
	}
}
