package ru.wmtool.web.operations.controller;

import ru.wmtool.web.operations.detail.OperationDetailPresenter;
import ru.wmtool.web.operations.detail.OperationDetailView;
import ru.wmtool.web.operations.grid.OperationsHistoryPresenter;
import ru.wmtool.web.operations.grid.OperationsHistoryView;

public interface OperationsHistoryBuilder {

	/**
	 * Получить вью общего представления истории операций.
	 * 
	 * @return
	 */
	OperationsHistoryView getOperationsHistoryView();

	/**
	 * Установить вью общего представления истории операций.
	 * 
	 * @return
	 */
	void setOperationsHistoryView(OperationsHistoryView operationsHistoryView);

	/**
	 * Получить презентер общего представления операций.
	 * 
	 * @return
	 */
	OperationsHistoryPresenter getOperationsHistoryPresenter();

	/**
	 * Установить презентер общего представления операций.
	 * 
	 * @param operationsHistoryPresenter
	 */
	void setOperationsHistoryPresenter(OperationsHistoryPresenter operationsHistoryPresenter);

	/**
	 * Получить вью детального представления операции.
	 * 
	 * @return
	 */
	OperationDetailView getOperationDetailView();

	/**
	 * Установить вью детального представления операции.
	 * 
	 * @param operationDetailView
	 */
	void setOperationDetailView(OperationDetailView operationDetailView);

	/**
	 * Получить презентера детального представления операции.
	 * 
	 * @return
	 */
	OperationDetailPresenter getOperationDetailPresenter();

	/**
	 * Установить презентера детального представления операции.
	 * 
	 * @param operationDetailPresenter
	 */
	void setOperationDetailPresenter(OperationDetailPresenter operationDetailPresenter);

}
