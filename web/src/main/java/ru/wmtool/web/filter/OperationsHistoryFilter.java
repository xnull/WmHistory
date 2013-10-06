package ru.wmtool.web.filter;

import java.util.Calendar;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.web.operations.grid.OperationsHistoryView;

public interface OperationsHistoryFilter {
	
	void filterOperations(Calendar beginDate, Calendar endDate);
	
	void setOperationsHistoryView(OperationsHistoryView operationsHistoryView);
	
	void setOperationsHistoryService(OperationsHistoryService operationsHistoryService);
}
