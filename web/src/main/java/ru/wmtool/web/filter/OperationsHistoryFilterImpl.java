package ru.wmtool.web.filter;

import java.util.Calendar;
import java.util.List;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.web.operations.grid.OperationsHistoryView;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationsHistoryFilterImpl extends GenericForwardComposer 
											implements OperationsHistoryFilter{

	private static final long serialVersionUID = 5085895441545174527L;
	//поле ввода начальной даты
	private Datebox beginDateBox;
	//поле ввода конечной даты
	private Datebox endDateBox;
	
	private OperationsHistoryView operationsHistoryView;
	
	private OperationsHistoryService operationsHistoryService;
	
	public void onChange$beginDateBox() {
		if(endDateBox.getValue() != null){
			filter();
		}
	}
	
	public void onChange$endDateBox() {
		if(beginDateBox.getValue() != null){
			filter();
		}
	}
	
	private void filter(){
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(beginDateBox.getValue());
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(endDateBox.getValue());
		filterOperations(beginDate,endDate);		
	}
	
	@Override
	public void filterOperations(Calendar beginDate, Calendar endDate) {
		List<WmOperation> operations = operationsHistoryService.getOperationsForCurrentUser(null, null, null, null);

		System.out.println("!!!!!!operationsHistoryView from filter " + operationsHistoryView);
		operationsHistoryView.showOperationsHistory(operations);
	}

	@Override
	public void setOperationsHistoryView(
			OperationsHistoryView operationsHistoryView) {
		this.operationsHistoryView = operationsHistoryView;
	}

	@Override
	public void setOperationsHistoryService(
			OperationsHistoryService operationsHistoryService) {
		this.operationsHistoryService = operationsHistoryService;
	}

}
