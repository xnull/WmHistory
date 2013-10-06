package ru.wmtool.commons.xml;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Класс для удобной работы с xpath.
 * @author null (xrw.null@gmail.com)
 *
 */
public class XpathUtils {
	private final XPath xpath = XPathFactory.newInstance().newXPath();
	/**
	 * Получение списка узлов
	 * @param xmlNode
	 * @param xpathExpr
	 * @return
	 * @throws Throwable
	 */
	public NodeList getNodes(Node xmlNode, String xpathExpr) throws Throwable{
		Object result = evaluateXpathExpression(xmlNode, xpathExpr, XPathConstants.NODESET);
		if ( !(result instanceof NodeList) ) {
			throw new XPathExpressionException("XPath выражение некорректно");
		}
		return (NodeList) result;
	}
	
	public Node getNode(Node xmlNode, String xpathExpr) throws Throwable{
		Object result = evaluateXpathExpression(xmlNode, xpathExpr, XPathConstants.NODE);
		if ( !(result instanceof Node) ) {
			throw new XPathExpressionException("XPath выражение некорректно");
		}
		return (Node) result;
	}
			
	public Object evaluateXpathExpression(Node xmlNode, String xpathExpr, QName returnType) throws Throwable{
		XPathExpression expr = xpath.compile(xpathExpr);
		Object result = expr.evaluate(xmlNode, returnType);
		return result;
	}
}
