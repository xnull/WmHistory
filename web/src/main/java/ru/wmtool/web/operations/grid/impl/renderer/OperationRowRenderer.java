package ru.wmtool.web.operations.grid.impl.renderer;

import java.beans.PropertyDescriptor;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Detail;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelArray;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import ru.wmtool.commons.utils.DateUtils;
import ru.wmtool.web.operations.detail.impl.renderer.OperationDetailItemRenderer;
import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Рендерер строки в таблице операций (operationsGrid).
 * @author nazgul
 *
 */
public class OperationRowRenderer implements RowRenderer{

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
	public void render(Row row, Object data) throws Exception {
		if (!(data instanceof WmOperation)) {
			throw new IllegalArgumentException(new ClassCastException(String.format(
					"Cannot cast %s to %s", data.getClass(), WmOperation.class)));
		}
		// создаем detail (детализация операции)
		WmOperation operation = (WmOperation) data;
		Detail detail = new Detail();
		Listbox operationDetail = generateOperationDetail(operation);
		detail.appendChild(operationDetail);
		detail.setParent(row);
		
		// создаем строку
		for (String attributeName : COLUMN_OPERATION_ATTRIBUTES) {
			Object attributeValue = PropertyUtils.getProperty(operation, attributeName);
			String attributeStrValue = null;
			if (attributeValue instanceof Double) {
				attributeStrValue = DECIMAL_FOMAT.format(attributeValue);
			} 
			if (attributeValue instanceof Calendar){
				DateUtils dateUtils = new DateUtils();
				attributeStrValue = dateUtils.parseCalendar((Calendar)attributeValue, "yyyy-MM-dd HH:mm:ss");
			}
			 else {
				attributeStrValue = attributeValue.toString();
			}
			new Label(attributeStrValue).setParent(row);
			row.setValue(operation);
		}
		
	}
	
	private Listbox generateOperationDetail(WmOperation operation) {
		Listbox operationDetail = new Listbox();
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
				Object property = PropertyUtils.getProperty(operation, name);
				if(property == null){
					continue;	
				}
				String value = "";
				if(property instanceof Calendar){
					value = new DateUtils().parseCalendar((Calendar)property, "yyyy-MM-dd HH:mm:ss");
				} else {
					value = property.toString();
				}
				
				model.add(new SimpleEntry<String, String>(name, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		operationDetail.setModel(new ListModelArray(model));
		operationDetail.setItemRenderer(new OperationDetailItemRenderer());
		return operationDetail;
	}

}
