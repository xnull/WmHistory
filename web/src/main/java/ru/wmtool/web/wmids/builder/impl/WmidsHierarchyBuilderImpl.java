package ru.wmtool.web.wmids.builder.impl;

import ru.wmtool.web.wmids.builder.WmidsHierarchyBuilder;
import ru.wmtool.web.wmids.tree.WmidsHierarchyPresenter;
import ru.wmtool.web.wmids.tree.WmidsHierarchyView;

public class WmidsHierarchyBuilderImpl implements WmidsHierarchyBuilder{

	private WmidsHierarchyView wmidsHierarchyView;
	private WmidsHierarchyPresenter wmidsHierarchyPresenter;
	
	public static WmidsHierarchyBuilderImpl createNew(WmidsHierarchyView wmidsHierarchyView,
													  WmidsHierarchyPresenter wmidsHierarchyPresenter){
		WmidsHierarchyBuilderImpl builder = new WmidsHierarchyBuilderImpl();
		
		//связываем вью с презентером
		wmidsHierarchyView.setPresenter(wmidsHierarchyPresenter);
		wmidsHierarchyPresenter.setView(wmidsHierarchyView);
		
		//связываем презентеры с собой через медиатор
		wmidsHierarchyView.setWmidsHierarchyBuilder(builder);
		wmidsHierarchyPresenter.setWmidsHierarchyBuilder(builder);
		
		builder.setWmidsHierarchyView(wmidsHierarchyView);
		builder.setWmidsHierarchyPresenter(wmidsHierarchyPresenter);
		
		return builder;
	}
	
	
	@Override
	public WmidsHierarchyView getWmidsHierarchyView() {
		return wmidsHierarchyView;
	}

	@Override
	public void setWmidsHierarchyView(WmidsHierarchyView wmidsHierarchyView) {
		this.wmidsHierarchyView = wmidsHierarchyView;
	}

	@Override
	public WmidsHierarchyPresenter getWmidsHierarchyPresenter() {
		return wmidsHierarchyPresenter;
	}

	@Override
	public void setWmidsHierarchyPresenter(
			WmidsHierarchyPresenter wmidsHierarchyPresenter) {
		this.wmidsHierarchyPresenter = wmidsHierarchyPresenter;
	}

}
