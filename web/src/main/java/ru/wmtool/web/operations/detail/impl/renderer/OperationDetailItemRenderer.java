package ru.wmtool.web.operations.detail.impl.renderer;

import java.util.AbstractMap.SimpleEntry;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class OperationDetailItemRenderer implements ListitemRenderer {

	@Override
	public void render(Listitem item, Object data) throws Exception {
		if (!(data instanceof SimpleEntry)) {
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("unchecked")
		SimpleEntry<String, String> entry = (SimpleEntry<String, String>) data;

		String name = entry.getKey();
		String value = entry.getValue();
		
		new Listcell(name).setParent(item);
		new Listcell(value).setParent(item);
	}

}
