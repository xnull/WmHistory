package ru.wmtool.test.webmoney.xmlinterfaces.history;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import ru.wmtool.commons.xml.JAXBUtils;
import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.webmoney.xmlinterfaces.history.History;

/**
 * Тест корректности маппинга хмл документа с иторией операций на класс
 * 
 * @author null
 */
public class HistoryMappingTest {

	/**
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void checkMapping() throws ParseException {
		XmlUtils xmlUtils = new XmlUtils();
		Document historyDoc = null;
		try {
			historyDoc = xmlUtils.load(getHistoryXmlDocFileName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		JAXBUtils jaxbUtils = new JAXBUtils();
		History history = null;
		try {
			history = jaxbUtils.xmlDocumentToClass(historyDoc, History.class);
		} catch (JAXBException e) {
			Assert.fail();
		}
		
		assertEquals(history.getReqn(), new Integer(1));
		assertEquals(history.getRetval(), new Integer(0));
		assertEquals(history.getOperations().getCnt(), new Integer(9));
		assertEquals(history.getOperations().getOperationList().size(), 9);
		assertEquals(history.getOperations().getOperationList().get(0).getId(), new Integer(560921650));
	
		assertEquals(history.getOperations().getOperationList().get(0).getDatecrt(), expectedDate());
	}

	private Calendar expectedDate() throws ParseException {
		String datecrt = "20110606 07:56:28";
		Calendar date = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		date.setTime(formatter.parse(datecrt));
		return date;
	}

	/**
	 * Получить путь к файлу истории операций
	 * 
	 * @return
	 */
	private String getHistoryXmlDocFileName() {
		String separator = System.getProperty("file.separator");
		String fileName = this.getClass().getPackage().getName().replace(".", separator) + separator + "history.xml";		
		String path = ClassLoader.getSystemResource(fileName).toString();
		return path;
	}
	
	
}