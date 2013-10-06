package ru.wmtool.test.xml;

import junit.framework.Assert;

import org.junit.Test;
import org.w3c.dom.Document;

import ru.wmtool.commons.xml.XmlUtils;

public class XmlUtilsTest {
	@Test
	public void StringToDocumentCheck() {
		Document doc = getDocumentFromString("<you/>");
		Assert.assertNotNull(doc);
		Assert.assertEquals(doc.getDocumentElement().getNodeName(), "you");
	}

	@Test
	public void StringToDocumentNullChecker() {
		Assert.assertNull(getDocumentFromString(null));
	}
	
	//TODO Дописать тест, на проверку всех методов в XmlUtils
	
	private Document getDocumentFromString(String xmlStr) {
		Document doc = null;
		XmlUtils xmlUtils = new XmlUtils();
		try {
			doc = xmlUtils.stringToDocument(xmlStr);
		} catch (Throwable e) {
			Assert.fail("Parse error");
		}
		return doc;
	}
}
