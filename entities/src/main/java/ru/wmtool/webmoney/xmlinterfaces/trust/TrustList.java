package ru.wmtool.webmoney.xmlinterfaces.trust;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.wmtool.commons.xml.JAXBUtils;

/**
 * Класс списка управления по доверию
 * http://wiki.webmoney.ru/wiki/show/Интерфейс+X15
 * 
 * @author null
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "w3s.response")
public class TrustList implements Serializable {
	private static final long serialVersionUID = -5878235688921304224L;
	
	@XmlElement
	private Integer reqn;
	
	@XmlElement
	private Integer retval;

	@XmlElement
	private String retdesc;
	
	@XmlElement(name = "trustlist")
	private Trusts trusts;
	
	/**
	 * номер запроса	
	 * @return
	 */
	public Integer getReqn() {
		return reqn;
	}

	/**
	 * код выполнения
	 * @return
	 */
	public Integer getRetval() {
		return retval;
	}
	
	public String getRetvalErrorDescription(){
		return RetvalErrors.getStatus(retval);
	}
	
	/**
	 * Описание кода выполнения
	 * @return
	 */
	public String getRetvalDescription(){
		return RetvalErrors.getStatus(retval);		
	}

	/**
	 * расшифровка кода выполнения
	 * @return
	 */
	public String getRetdesc() {
		return retdesc;
	}

	/**
	 * список доверяемых кошельков ВМ-идентификатора, и соответствующих им 
	 * идентификаторов master
	 * @return
	 */
	public Trusts getTrusts() {
		return trusts;
	}
	
	@Override
	public String toString() {
		JAXBUtils jaxUtils = new JAXBUtils();
		return jaxUtils.classToXmlString(this);
	}
}
