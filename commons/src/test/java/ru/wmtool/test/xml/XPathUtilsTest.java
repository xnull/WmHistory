package ru.wmtool.test.xml;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ru.wmtool.commons.xml.XmlUtils;
import ru.wmtool.commons.xml.XpathUtils;

public class XPathUtilsTest {
	
	@Test
	public void checkXpathUtils() throws Throwable{
		XpathUtils xpathUtils = new XpathUtils();
		
		NodeList findedNodes = xpathUtils.getNodes(createTestXmlDoc(), "//root/*");		
		assertEquals(findedNodes.getLength(), 2);
		
		Node findedNode = xpathUtils.getNode(createTestXmlDoc(), "//root/you");
		assertEquals(findedNode.getNodeName(), "you");
		
		//TODO дописать тест, на проверку exceptions для методов XpathUtils
	}	
	
	
	private Document createTestXmlDoc() throws Throwable{
		XmlUtils xmlUtils = new XmlUtils();
		Document result = xmlUtils.stringToDocument("<root><you/><you2/></root>");
		
		return result;
	}
}
