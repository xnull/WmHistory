package ru.wmtool.webmoney.xmlinterfaces.history;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.wmtool.commons.xml.JAXBUtils;

/**
 * Класс пользовательского запроса истории операций с сайта вебмани.
 * http://wiki.webmoney.ru/wiki/show/Интерфейс+X3
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "w3s.request")
public class HistoryRequest {
	@XmlElement(name="reqn")
	private Integer reqN;
	@XmlElement
	private String wmid;
	@XmlElement
	private String sign;
	
	@XmlElement(name="getoperations")
	private HistoryRequestParameters requestParameters;

	public HistoryRequest(HistoryRequestParameters requestParameters) {
		this.requestParameters = requestParameters;
	}

	public HistoryRequest() {
	}

	public Integer getReqN() {
		return reqN;
	}

	/**
	 * 
	 * @param reqN
	 *            номер запроса. целое без знака, макс. количество цифр - 15;
	 *            всегда должен быть больше номера предыдущего запроса на
	 *            перевод средств!!!
	 */
	public void setReqN(Integer reqN) {
		this.reqN = reqN;
	}

	/**
	 * WMID подписавшего запрос
	 * 
	 * @return
	 */
	public String getWmid() {
		return wmid;
	}

	/**
	 * WMID подписавшего запрос. используется только при авторизации с ключами
	 * WM Keeper Classic
	 * 
	 * @param wmid
	 */
	public void setWmid(String wmid) {
		this.wmid = wmid;
	}

	/**
	 * подпись запроса. Формируется из параметров: purse+reqn
	 * 
	 * @return
	 */
	public String getSign() {
		return sign;
	}

	public HistoryRequestParameters getRequestParameters() {
		return requestParameters;
	}

	@Override
	public String toString() {
		JAXBUtils jaxUtils = new JAXBUtils();
		return jaxUtils.classToXmlString(this);
	}

	/**
	 * подпись запроса. Формируется из параметров: purse+reqn. используется
	 * только при авторизации с ключами WM Keeper Classic
	 * 
	 * @param sign
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
}