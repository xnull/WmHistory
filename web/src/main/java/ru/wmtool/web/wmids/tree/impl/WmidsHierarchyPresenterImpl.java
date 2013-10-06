package ru.wmtool.web.wmids.tree.impl;

import ru.wmtool.interfaces.OperationsHistoryService;
import ru.wmtool.security.User;
import ru.wmtool.web.operations.controller.impl.OperationsHistoryController;
import ru.wmtool.web.wmids.builder.WmidsHierarchyBuilder;
import ru.wmtool.web.wmids.tree.WmidsHierarchyPresenter;
import ru.wmtool.web.wmids.tree.WmidsHierarchyView;

public class WmidsHierarchyPresenterImpl implements WmidsHierarchyPresenter{
	
	private WmidsHierarchyView view;
	private WmidsHierarchyBuilder builder;
	// services
	@SuppressWarnings("unused")
	private OperationsHistoryService operationsHistoryService;

	@SuppressWarnings("unused")
	private OperationsHistoryController controller;
	
	@Override
	public void loadWmids(User user) {
		view.showWmids(user.getWmids());
	}

	@Override
	public void setView(WmidsHierarchyView view) {
		this.view = view;
	}

	@Override
	public void setWmidsHierarchyBuilder(WmidsHierarchyBuilder builder) {
		this.builder = builder;
	}

}
