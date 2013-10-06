package ru.wmtool.test.webmoney;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import ru.wmtool.httpclient.HttpClientImpl;
import ru.wmtool.webmoney.SiteHistory;
import ru.wmtool.webmoney.signer.WmSignerImpl;
import ru.wmtool.webmoney.xmlinterfaces.history.History;
import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequest;
import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequestParameters;

/**
 * Тестирование получения реальной истории операций с сафта вебмани
 * @author null
 *
 */
public class SiteHistoryTest {

	@Test
	public void testGetHistoryFromsite() throws Throwable {
		SiteHistory siteHistory = new SiteHistory(new HttpClientImpl());

		HistoryRequest historyRequest = createHistoryRequest();

		History history = siteHistory.getHistoryFromSite(historyRequest);

		System.out.println("Ответ сервера: " + history.toXmlString());
		
		assertEquals(history.getRetval(), new Integer(0));
		//assertEquals(new Integer(history.getOperations().getOperationList().size()), history.getOperations().getCnt());
	}

	private HistoryRequest createHistoryRequest() {
		Calendar dateStart = Calendar.getInstance();
		dateStart.add(Calendar.DAY_OF_MONTH, -90);
		
		String purseNumber = "R188728521832";
		
		HistoryRequestParameters histParams = new HistoryRequestParameters(purseNumber, dateStart,
				Calendar.getInstance());
		
		HistoryRequest historyRequest = new HistoryRequest(histParams);		
		historyRequest.setWmid("294124932358");
		
		Integer reqN = new Integer(1);
		historyRequest.setReqN(reqN);
		
		WmSignerImpl signer = new WmSignerImpl();
		String signStr = signer.sign(purseNumber + reqN);
		historyRequest.setSign(signStr);
		
		return historyRequest;
	}
}
