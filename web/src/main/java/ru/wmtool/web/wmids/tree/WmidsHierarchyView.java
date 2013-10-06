package ru.wmtool.web.wmids.tree;

import java.util.List;

import ru.wmtool.web.wmids.builder.WmidsHierarchyBuilder;
import ru.wmtool.webmoney.Wmid;

public interface WmidsHierarchyView {

	/**
	 * Отображает все вмиды пользователя.
	 * @param wmids список вмидов.
	 */
	public void showWmids(List<Wmid> wmids);
	
	/**
	 * Установить презентер.
	 * @param presenter
	 */
	public void setPresenter(WmidsHierarchyPresenter presenter);
	
	public void setWmidsHierarchyBuilder(WmidsHierarchyBuilder builder);
}
