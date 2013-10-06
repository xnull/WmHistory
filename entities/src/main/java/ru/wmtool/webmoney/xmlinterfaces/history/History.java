package ru.wmtool.webmoney.xmlinterfaces.history;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.wmtool.commons.xml.JAXBUtils;

/**
 * История операций. Xml интерфейс вебмани - X3
 * http://www.webmoney.ru/rus/developers/interfaces/xml/ophistory/index.shtml
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "w3s.response")
public class History {
	@XmlElement
	private Integer reqn;

	@XmlElement
	private Integer retval;

	@XmlElement
	private String retdesc;

	@XmlElement(name = "operations")
	private WmOperations operations;

	public Integer getReqn() {
		return reqn;
	}

	public Integer getRetval() {
		return retval;
	}

	public String getRetdesc() {
		return retdesc;
	}

	public WmOperations getOperations() {
		return operations;
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(String.format("reqn = %s, retval = %s, retdesc = %s", reqn, retval, retdesc));
		return result.toString();
	}
	
	public String toXmlString(){
		JAXBUtils jaxUtils = new JAXBUtils();
		return jaxUtils.classToXmlString(this);
	}
}
