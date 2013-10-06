package ru.wmtool.dao;

public class EmptyResultException extends Exception {
	
	private static final long serialVersionUID = 2162118325257152004L;
	private Class<?> clazz;
	
	public EmptyResultException() {
		super();
	}

	public <T> EmptyResultException(Class<T> class1) {
		super("Не было найдено ни одной записи сущности " + class1.getName());
		clazz = class1;
	}
	
	/**
	 * Получить класс сущности
	 * @return класс
	 */
	public Class<?> getClazz() {
		return clazz;
	}
}
