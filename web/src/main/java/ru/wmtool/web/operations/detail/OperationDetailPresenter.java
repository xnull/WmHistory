package ru.wmtool.web.operations.detail;

import ru.wmtool.web.operations.controller.impl.OperationsHistoryController;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public interface OperationDetailPresenter {

	void setView(OperationDetailView view);

	void setOperationsHistoryController(OperationsHistoryController controller);

	void showOperationDetail(WmOperation operation);

}
