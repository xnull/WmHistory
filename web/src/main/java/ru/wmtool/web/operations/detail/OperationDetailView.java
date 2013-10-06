package ru.wmtool.web.operations.detail;

import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public interface OperationDetailView {

	void setPresenter(OperationDetailPresenter operationDetailPresenter);

	void showOperationDetail(WmOperation operation);

}
