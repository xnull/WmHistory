package ru.wmtool.test.webmoney.xmlinterfaces.history;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequest;
import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequestParameters;

/**
 * Тест класса - параметры запроса истории пользователя с сайта вебмани
 * 
 * @author null (xrw.null@gmail.com)
 */
public class RequestParametersTest {

	@Test
	public void createRequest() throws Throwable {
		HistoryRequestParameters reqParam = new HistoryRequestParameters("R123456789012", GregorianCalendar
				.getInstance(), GregorianCalendar.getInstance());
		HistoryRequest request = new HistoryRequest(reqParam);
		Assert.assertEquals(request.getRequestParameters().getPurseNumber(), "R123456789012");
	}
}
