package ru.wmtool.web.wmids.builder;

import ru.wmtool.web.wmids.tree.WmidsHierarchyPresenter;
import ru.wmtool.web.wmids.tree.WmidsHierarchyView;

public interface WmidsHierarchyBuilder {
	/**
	 * Получить вью общего представления истории операций.
	 * 
	 * @return
	 */
	WmidsHierarchyView getWmidsHierarchyView();

	/**
	 * Установить вью общего представления истории операций.
	 * 
	 * @return
	 */
	void setWmidsHierarchyView(WmidsHierarchyView wmidsHierarchyView);

	/**
	 * Получить презентер общего представления операций.
	 * 
	 * @return
	 */
	WmidsHierarchyPresenter getWmidsHierarchyPresenter();

	/**
	 * Установить презентер общего представления операций.
	 * 
	 * @param operationsHistoryPresenter
	 */
	void setWmidsHierarchyPresenter(WmidsHierarchyPresenter wmidsHierarchyPresenter);

}
