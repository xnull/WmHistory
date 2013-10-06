package ru.wmtool.webmoney.xmlinterfaces.trust;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс входящий в запрос.
 * Прдставляет собой вмид, по которому необходимо получить список доверия.
 * 
 * Указывается ВМ-идентификатор, по которому 
 * необходимо получить список доверия, возвращенный запросом список 
 * будет эквивалентен списку, который может быть получен 
 * при авторизации этим идентификатором вручную в сервисе security.webmoney.ru.
 * @author null (xrw.null@gmail.com)
 *
 */
@XmlRootElement(name="gettrustlist")
public class TrustedWmidRequest {
	
	private String wmid;

	public String getWmid() {
		return wmid;
	}

	public void setWmid(String wmid) {
		this.wmid = wmid;
	}
}
