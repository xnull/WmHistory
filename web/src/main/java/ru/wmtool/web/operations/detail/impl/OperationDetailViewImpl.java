package ru.wmtool.web.operations.detail.impl;

import java.beans.PropertyDescriptor;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import ru.wmtool.web.operations.detail.OperationDetailPresenter;
import ru.wmtool.web.operations.detail.OperationDetailView;
import ru.wmtool.web.operations.detail.impl.renderer.OperationDetailItemRenderer;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationDetailViewImpl extends GenericForwardComposer implements OperationDetailView {
	private static final long serialVersionUID = -5167642557106437607L;

	@SuppressWarnings("unused")
	private OperationDetailPresenter presenter;

	private Window operationDetailWindow;
	private Listbox operationDetail;

	@Override
	public void setPresenter(OperationDetailPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void showOperationDetail(WmOperation operation) {
		PropertyDescriptor[] propertyDescriptors = PropertyUtils
				.getPropertyDescriptors(WmOperation.class);
		
		@SuppressWarnings("rawtypes")
		List<SimpleEntry> model = new ArrayList<SimpleEntry>();
		
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equals("class")) {
				continue;
			}
			try {
				String name = propertyDescriptor.getName();
				String value = PropertyUtils.getProperty(operation, name).toString();
				model.add(new SimpleEntry<String, String>(name, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		operationDetail.setModel(new ListModelArray(model));
		operationDetail.setItemRenderer(new OperationDetailItemRenderer());

		try {
			operationDetailWindow.setTitle("Operation Detail " + operation.getWminvid());
			if (!operationDetailWindow.inModal()) {
				operationDetailWindow.setMode(Window.MODAL);
			}
			operationDetailWindow.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
