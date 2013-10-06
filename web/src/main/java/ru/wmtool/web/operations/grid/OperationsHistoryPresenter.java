package ru.wmtool.web.operations.grid;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.web.operations.controller.impl.OperationsHistoryController;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public interface OperationsHistoryPresenter {

	/**
	 * Загрузить операции пользователя.
	 */
	void loadOperations();

	/**
	 * Получить операции для текущего пользователя - начиная с beginIndex в
	 * количестве countOperations.
	 * 
	 * @param beginIndex
	 * @param countOperations
	 */
	void loadOperations(int beginIndex, int countOperations);

	/**
	 * Получить количество операций для данного пользователя.
	 * 
	 * @return
	 */
	int getOperationsCount();

	/**
	 * Установить вью.
	 * 
	 * @param view
	 */
	void setView(OperationsHistoryView view);

	/**
	 * Установить сервис.
	 * 
	 * @param operationsHistoryService
	 */
	void setOperationsHistoryService(OperationsHistoryService operationsHistoryService);

	void setOperationsHistoryController(OperationsHistoryController controller);

	void showOperationDetail(WmOperation operation);

}