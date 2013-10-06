package ru.wmtool.web.operations.controller.impl;

import ru.wmtool.web.filter.OperationsHistoryFilter;
import ru.wmtool.web.operations.controller.OperationsHistoryBuilder;
import ru.wmtool.web.operations.detail.OperationDetailPresenter;
import ru.wmtool.web.operations.detail.OperationDetailView;
import ru.wmtool.web.operations.grid.OperationsHistoryPresenter;
import ru.wmtool.web.operations.grid.OperationsHistoryView;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationsHistoryControllerImpl implements OperationsHistoryBuilder,
		OperationsHistoryController {

	private OperationsHistoryPresenter operationsHistoryPresenter;
	private OperationsHistoryView operationsHistoryView;

	private OperationDetailView operationDetailView;
	private OperationDetailPresenter operationDetailPresenter;
	
	private OperationsHistoryFilter operationsHistoryFilter;

	public static OperationsHistoryControllerImpl createNew(OperationsHistoryView operationsHistoryView,
			OperationsHistoryPresenter operationsHistoryPresenter,
			OperationDetailView operationDetailView,
			OperationDetailPresenter operationDetailPresenter,
			OperationsHistoryFilter operationsHistoryFilter) {
		OperationsHistoryControllerImpl controller = new OperationsHistoryControllerImpl();

		// связываем вью с презентером
		operationsHistoryView.setPresenter(operationsHistoryPresenter);
		operationsHistoryPresenter.setView(operationsHistoryView);

		// связываем вью с презентером
		operationDetailView.setPresenter(operationDetailPresenter);
		operationDetailPresenter.setView(operationDetailView);

		// связываем презентеры между собой через медиатор
		operationsHistoryPresenter.setOperationsHistoryController(controller);
		operationDetailPresenter.setOperationsHistoryController(controller);
		
		controller.setOperationsHistoryView(operationsHistoryView);
		controller.setOperationsHistoryPresenter(operationsHistoryPresenter);
		
		controller.setOperationDetailView(operationDetailView);
		controller.setOperationDetailPresenter(operationDetailPresenter);
		
		//связываем фильтр
		operationsHistoryFilter.setOperationsHistoryView(operationsHistoryView);
		System.out.println("!!!!!!operationsHistoryView " + operationsHistoryView);
		return controller;
	}

	@Override
	public OperationsHistoryView getOperationsHistoryView() {
		return operationsHistoryView;
	}

	@Override
	public void setOperationsHistoryView(OperationsHistoryView operationsHistoryView) {
		this.operationsHistoryView = operationsHistoryView;
	}

	@Override
	public OperationsHistoryPresenter getOperationsHistoryPresenter() {
		return operationsHistoryPresenter;
	}

	@Override
	public void setOperationsHistoryPresenter(OperationsHistoryPresenter operationsHistoryPresenter) {
		this.operationsHistoryPresenter = operationsHistoryPresenter;
	}

	@Override
	public OperationDetailPresenter getOperationDetailPresenter() {
		return operationDetailPresenter;
	}

	@Override
	public OperationDetailView getOperationDetailView() {
		return operationDetailView;
	}

	@Override
	public void setOperationDetailPresenter(OperationDetailPresenter operationDetailPresenter) {
		this.operationDetailPresenter = operationDetailPresenter;
	}

	@Override
	public void setOperationDetailView(OperationDetailView operationDetailView) {
		this.operationDetailView = operationDetailView;
	}

	@Override
	public void showOperationDetail(WmOperation operation) {
		operationDetailPresenter.showOperationDetail(operation);
	}

	public OperationsHistoryFilter getOperationsHistoryFilter() {
		return operationsHistoryFilter;
	}

	public void setOperationsHistoryFilter(OperationsHistoryFilter operationsHistoryFilter) {
		this.operationsHistoryFilter = operationsHistoryFilter;
	}
}
