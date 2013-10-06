package ru.wmtool.webmoney.xmlinterfaces.history;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Параметры запроса истории пользователя с сайта вебмани
 * http://wiki.webmoney.ru/wiki/show/Интерфейс+X3
 * @see ru.wmtool.webmoney.xmlinterfaces.history.HistoryRequest
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getoperations")
public class HistoryRequestParameters {
	@XmlElement
	private String purse;

	/**
	 * номер операции (в системе WebMoney). целое число > 0, максимально 231-1
	 */
	@XmlElement
	private Integer wmtranid;

	/**
	 * номер перевода в системе учета отправителя; любое целое число без знака
	 */
	@XmlElement
	private Integer tranid;

	/**
	 * номер счета (в системе WebMoney) по которому выполнялась операция
	 */
	@XmlElement
	private Integer wminvid;

	/**
	 * номер счета в системе учета магазина; любое целое число без знака
	 */
	private Integer orderid;

	/**
	 * минимальное время и дата выполнения операции. ГГГГММДД ЧЧ:ММ:СС
	 */
	@XmlElement(name="datestart")
	private String dateStart;

	/**
	 * максимальное время и дата выполнения операции. ГГГГММДД ЧЧ:ММ:СС
	 */
	@XmlElement(name="datefinish")
	private String dateFinish;

	/**
	 * Параметры запроса истории операций
	 * @param purseNumber
	 * @param dateStart
	 * @param dateFinish
	 */
	public HistoryRequestParameters(String purseNumber, Calendar dateStart, Calendar dateFinish) {
		this.purse = purseNumber;
		this.dateStart = dateFormatter(dateStart);
		this.dateFinish = dateFormatter(dateFinish);
	}

	/**
	 * Преобразование даты к виду нужному сайту вебмани. <br>
	 * См. http://www.webmoney.ru/rus/developers/interfaces/xml/ophistory/index.
	 * shtml
	 */
	private String dateFormatter(Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formattedDate = formatter.format(date.getTime());
		return formattedDate;
	}

	public HistoryRequestParameters() {
	}

	public Integer getWmtranid() {
		return wmtranid;
	}

	public void setWmtranid(Integer wmtranid) {
		this.wmtranid = wmtranid;
	}

	public Integer getTranid() {
		return tranid;
	}

	public void setTranid(Integer tranid) {
		this.tranid = tranid;
	}

	public Integer getWminvid() {
		return wminvid;
	}

	public void setWminvid(Integer wminvid) {
		this.wminvid = wminvid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getPurseNumber() {
		return purse;
	}

	public String getDatestart() {
		return dateStart;
	}

	public String getDatefinish() {
		return dateFinish;
	}
}
