package ru.wmtool.test.webmoney.xmlinterfaces.history;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import ru.wmtool.commons.xml.JAXBUtils;
import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequest;

/**
 * Тест класса - хмл запрос истории операций
 * 
 * @author null (xrw.null@gmail.com)
 * 
 */
public class HistoryRequestTest {
	@Test
	public void toStringChecker() throws Throwable {		
		HistoryRequest historyRequest = new HistoryRequest();
		HistoryRequest expectedHistoryRequest = getExpectedHistoryRequest();
		
		Assert.assertEquals(historyRequest.toString(), expectedHistoryRequest.toString());
	}

	private HistoryRequest getExpectedHistoryRequest()
			throws ParserConfigurationException, JAXBException {
		XmlUtils xmlUtils = new XmlUtils();
		Document expectedXmlResult = xmlUtils.createDocument();
		expectedXmlResult.appendChild(expectedXmlResult.createElement("w3s.request"));
		JAXBUtils jaxUtils = new JAXBUtils();
		HistoryRequest expectedHistoryRequest = jaxUtils.xmlDocumentToClass(expectedXmlResult, HistoryRequest.class);
		return expectedHistoryRequest;
	}
}
