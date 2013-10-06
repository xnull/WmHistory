package ru.wmtool.web.operations.grid.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Paging;
import org.zkoss.zul.event.PagingEvent;

import ru.wmtool.web.operations.grid.OperationsHistoryPresenter;
import ru.wmtool.web.operations.grid.OperationsHistoryView;
import ru.wmtool.web.operations.grid.impl.renderer.OperationRowRenderer;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationsHistoryViewImpl extends GenericForwardComposer implements
		OperationsHistoryView {
	private static final Logger log = LogManager.getLogger(OperationsHistoryViewImpl.class);
	
	private static final long serialVersionUID = 5058817326546361376L;
	
	// сетка, отображающая историю операций
	private Grid operationsGrid;
	// выбор страниц 
	private Paging paging;

	// количество кнопочек с циферками
	private static final int PAGE_INCREMENT = 5;

	// количество операций на странице
	private static final int pageSize = 10;
	// стартовая операция на странице
	private int startOperationIndex = 0;

	// презентер
	private OperationsHistoryPresenter presenter;

	@Override
	public void setPresenter(OperationsHistoryPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// инит пагинатора
		initPaging();
		// загрузка первой страницы с данными
		presenter.loadOperations(startOperationIndex, pageSize);
	}

	/**
	 * init paginator
	 */
	private void initPaging() {
		// количество операций
		int operationsCount = presenter.getOperationsCount();
		// количество страниц
		int pagesCount = operationsCount / pageSize;
		// определяем количество кнопочек с номерами страниц
		if (pagesCount >= PAGE_INCREMENT) {
			paging.setPageIncrement(PAGE_INCREMENT);
		}
		// количество операций страниц
		paging.setPageSize(pageSize);
		// общее количество оперций
		paging.setTotalSize(operationsCount);
	}
	/**
	 * Выбор страницы 
	 * @param event
	 * @throws Exception
	 */
	public void onPaging$paging(ForwardEvent event) throws Exception {
		PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
		startOperationIndex = pagingEvent.getActivePage() * pageSize;
		presenter.loadOperations(startOperationIndex, pageSize);
	}


	@Override
	public void showOperationsHistory(List<WmOperation> operations) {
		ListModel model = new ListModelArray(operations);
		//FIXME: Выяснить, почему operationsGrid = null при попытке фильтрации. 
		if(operationsGrid == null){
			log.error("Мда");
		}
			
		operationsGrid.setModel(model);
		operationsGrid.setRowRenderer(new OperationRowRenderer());
	}

}
