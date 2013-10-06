package ru.wmtool.test.webmoney.xmlinterfaces.trust;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import ru.wmtool.commons.xml.JAXBUtils;
import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustList;

/**
 * Проверка маппинга списка доверия
 * @author null
 *
 */
public class TrustListTest {

	@Test
	public void checkMapping(){
		XmlUtils xmlUtils = new XmlUtils();
		Document trustListXmlDoc = null;
		try {
			trustListXmlDoc = xmlUtils.load(getTrustListXmlDocFileName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		JAXBUtils jaxbUtils = new JAXBUtils();
		TrustList trustList = null;
		try {
			trustList = jaxbUtils.xmlDocumentToClass(trustListXmlDoc, TrustList.class);
		} catch (JAXBException e) {
			Assert.fail();
		}
		
		assertEquals(trustList.getReqn(), new Integer(1));
		assertEquals(trustList.getRetval(), new Integer(0));
		assertEquals(trustList.getTrusts().getCnt(), new Integer(10));
		assertEquals(trustList.getTrusts().getTrustList().size(), 10);
		assertEquals(trustList.getTrusts().getTrustList().get(0).getPurse(), "R675010658902");
	}
	
	/**
	 * Получить путь к файлу доверий
	 * 
	 * @return
	 */
	private String getTrustListXmlDocFileName() {
		String separator = System.getProperty("file.separator");
		String fileName = this.getClass().getPackage().getName().replace(".", separator) + separator + "trustlist.xml";		
		String path = ClassLoader.getSystemResource(fileName).toString();
		return path;
	}
}
