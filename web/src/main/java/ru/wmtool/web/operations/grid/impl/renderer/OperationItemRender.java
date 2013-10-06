package ru.wmtool.web.operations.grid.impl.renderer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

public class OperationItemRender implements ListitemRenderer {

	private static List<String> COLUMN_OPERATION_ATTRIBUTES = new ArrayList<String>() {
		private static final long serialVersionUID = 226302760886671545L;

		{
			add("datecrt");
			add("pursesrc");
			add("pursedest");
			add("amount");
			add("comiss");
			add("rest");
			add("desc");

		}
	};

	private static DecimalFormat DECIMAL_FOMAT = new DecimalFormat("#.####");

	@Override
	public void render(Listitem item, Object data) throws Exception {
		if (!(data instanceof WmOperation)) {
			throw new IllegalArgumentException(new ClassCastException(String.format(
					"Cannot cast %s to %s", data.getClass(), WmOperation.class)));
		}

		WmOperation operation = (WmOperation) data;

		for (String attributeName : COLUMN_OPERATION_ATTRIBUTES) {
			Object attributeValue = PropertyUtils.getProperty(operation, attributeName);
			String attributeStrValue = null;
			if (attributeValue instanceof Double) {
				attributeStrValue = DECIMAL_FOMAT.format(attributeValue);
			} else {
				attributeStrValue = attributeValue.toString();
			}

			new Listcell(attributeStrValue).setParent(item);
			item.setValue(operation);
		}

	}

}
