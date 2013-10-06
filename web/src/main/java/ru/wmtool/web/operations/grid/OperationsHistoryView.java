package ru.wmtool.web.operations.grid;

import java.util.List;

import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Interface for View for operation viewing
 * 
 * @author Evgin
 * 
 */
public interface OperationsHistoryView {

	/**
	 * Заполнить таблицу данными операций.
	 * 
	 * @param operations
	 *            операции
	 */
	public void showOperationsHistory(List<WmOperation> operations);

	/**
	 * Установить презентер.
	 * 
	 * @param presenter
	 */
	public void setPresenter(OperationsHistoryPresenter presenter);

}
