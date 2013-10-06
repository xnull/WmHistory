package ru.wmtool.web.operations.grid.impl;

import java.util.List;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.web.operations.controller.impl.OperationsHistoryController;
import ru.wmtool.web.operations.grid.OperationsHistoryPresenter;
import ru.wmtool.web.operations.grid.OperationsHistoryView;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Презентер данных об операциях пользователя.
 * 
 * @author Evgin
 * 
 */
public class OperationsHistoryPresenterImpl implements OperationsHistoryPresenter {

	// view
	private OperationsHistoryView view;

	// services
	private OperationsHistoryService operationsHistoryService;

	private OperationsHistoryController controller;

	@Override
	public void setView(OperationsHistoryView view) {
		this.view = view;
	}

	@Override
	public void setOperationsHistoryService(OperationsHistoryService operationsHistoryService) {
		this.operationsHistoryService = operationsHistoryService;
	}

	@Override
	public void setOperationsHistoryController(OperationsHistoryController controller) {
		this.controller = controller;
	}

	//FIXME тута непонятно, что должен выполнить код. Женя нам поможет :)
	public void loadOperations() {
		// получаем все операции пользователя
		int operations = operationsHistoryService.getAllOperationsCountForCurrentUser();

		// устанавливаем операции вьюхи
		//view.showOperationsHistory(operations);
	}

	public void loadOperations(int beginIndex, int countOperations) {
		List<WmOperation> operations = operationsHistoryService.getOperationsForCurrentUser(
				null, null, beginIndex, countOperations);
		view.showOperationsHistory(operations);
	}

	public int getOperationsCount() {
		return operationsHistoryService.getAllOperationsCountForCurrentUser();
	}

	@Override
	public void showOperationDetail(WmOperation operation) {
		controller.showOperationDetail(operation);
	}
}
