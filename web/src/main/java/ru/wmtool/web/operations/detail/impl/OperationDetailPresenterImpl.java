package ru.wmtool.web.operations.detail.impl;

import ru.wmtool.web.operations.controller.impl.OperationsHistoryController;
import ru.wmtool.web.operations.detail.OperationDetailPresenter;
import ru.wmtool.web.operations.detail.OperationDetailView;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationDetailPresenterImpl implements OperationDetailPresenter {

	private OperationDetailView view;
	@SuppressWarnings("unused")
	private OperationsHistoryController controller;

	@Override
	public void setOperationsHistoryController(OperationsHistoryController controller) {
		this.controller = controller;
	}

	@Override
	public void setView(OperationDetailView view) {
		this.view = view;
	}

	@Override
	public void showOperationDetail(WmOperation operation) {
		view.showOperationDetail(operation);
	}

}
