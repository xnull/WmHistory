package ru.wmtool.webmoney.xmlinterfaces.trust;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ru.wmtool.commons.xml.JAXBUtils;

/**
 * Класс запроса на просмотр текущих настроек управления "по доверию".
 * Формат запроса:
 * <w3s.request>
 *  <reqn></reqn>
 *	<wmid></wmid>
 *	<sign>gettrustlist/wmid+reqn</sign>
 *	<gettrustlist>
 *		<wmid></wmid>
 *	</gettrustlist>
 *</w3s.request>

 * @author null (xrw.null@gmail.com)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "w3s.request")
public class TrustListRequest {

	/**
	 * номер запроса
	 * целое без знака, макс. количество цифр – 15; всегда должен быть больше номера предыдущего запроса
	 */
	@XmlElement(name="reqn")
	private Integer reqN;
	
	/**
	 * WMID подписавшего запрос.
	 * используется только при авторизации с ключамиWM Keeper Classic
	 */
	@XmlElement
	private String wmid;
	
	/**
	 * подпись запроса.
	 * формируется из параметров: gettrustlist\wmid+reqn 
	 * используется только при авторизации с ключами WM Keeper Classic
	 */
	@XmlElement
	private String sign;
		
	@XmlElement(name="gettrustlist")	
	private TrustedWmidRequest trustedWmid;
	
	/**
	 * Фактически в запросе идентификаторы wmid и gettrustlist\wmid должны совпадать, 
	 * так как подписать запрос может только идентификатор по которому необходимо получить список.
	 * @param trustedWmid
	 */
	public TrustListRequest(TrustedWmidRequest trustedWmid){
		this.trustedWmid = trustedWmid;
	}
	
	@Override
	public String toString() {
		JAXBUtils jaxUtils = new JAXBUtils();
		return jaxUtils.classToXmlString(this);
	}
	
	public TrustListRequest(){}

	public Integer getReqN() {
		return reqN;
	}

	public void setReqN(Integer reqN) {
		this.reqN = reqN;
	}

	public String getWmid() {
		return wmid;
	}

	public void setWmid(String wmid) {
		this.wmid = wmid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public TrustedWmidRequest getTrustedWmid() {
		return trustedWmid;
	}

	public void setTrustedWmid(TrustedWmidRequest trustedWmid) {
		this.trustedWmid = trustedWmid;
	}
}
