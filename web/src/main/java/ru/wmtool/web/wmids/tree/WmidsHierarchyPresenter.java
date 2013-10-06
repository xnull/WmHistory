package ru.wmtool.web.wmids.tree;

import ru.wmtool.security.User;
import ru.wmtool.web.wmids.builder.WmidsHierarchyBuilder;

public interface WmidsHierarchyPresenter {
	/**
	 * Загрузить вмиды пользователя.
	 */
	public void loadWmids(User user);
	
	/**
	 * Установить вью.
	 * @param view
	 */
	public void setView(WmidsHierarchyView view);
	
	public void setWmidsHierarchyBuilder(WmidsHierarchyBuilder builder);
}
