package ru.wmtool.webmoney.xmlinterfaces.history;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ru.wmtool.commons.utils.DateUtils;
import ru.wmtool.webmoney.Purse;

/**
 * Класс операции из истории операций вебмани.
 */

//TODO перенести аннотации хмл(jaxb) маппинга в хмл файл  
@Entity
@Table(name = "operations")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "operations")
public class WmOperation implements Serializable {	
	private static final long serialVersionUID = 481312300274741060L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@XmlAttribute
	private Integer id;
	
	@ManyToOne
	private Purse purse;

	@XmlElement //маппинг на xml-файл
	@Column
	private String pursesrc;
	@XmlElement
	@Column
	private String pursedest;
	@XmlElement
	@Column
	private Double amount;
	@XmlElement
	@Column
	private Double comiss;
	@XmlElement
	@Column
	private Integer opertype;

	/**
	 * срок протекции сделки в днях. целое число от 0 до 255; если 0 - операция
	 * без протекции сделки
	 */
	@XmlElement
	@Column
	private Integer period;

	/**
	 * номер счета (в системе WebMoney), по которому выполняется перевод. целое
	 * число > 0; если 0 - перевод не по счету, максимально 2^31-1
	 */
	@XmlElement
	@Column
	private Long wminvid;

	/**
	 * номер счета (в системе магазина, выдавшего счет), по которому выполняется
	 * перевод. целое число > 0; если 0 - перевод не по счету, максимально
	 * 2^31-1
	 */
	@XmlElement
	@Column
	private Integer orderid;

	@XmlElement
	@Column
	private String desc;

	/**
	 * дата и время выполнения операции
	 */
	@XmlElement
	@Column
	private String datecrt;

	public Calendar getDatecrt() throws ParseException {
		DateUtils dateUtils = new DateUtils();
		return dateUtils.parseStr(datecrt, "yyyyMMdd HH:mm:ss");
	}

	public void setDatecrt(Calendar datecrt) {
		DateUtils dateUtils = new DateUtils();
		this.datecrt = dateUtils.parseCalendar(datecrt, "yyyyMMdd HH:mm:ss");
	}
	/**
	 * дата и время последнего изменения состояния операции
	 */
	@XmlElement
	@Column
	private String dateupd;
	
	public Calendar getDateupd() throws ParseException {
		DateUtils dateUtils = new DateUtils();
		return dateUtils.parseStr(dateupd, "yyyyMMdd HH:mm:ss");
	}

	public void setDateupd(Calendar dateupd) {
		DateUtils dateUtils = new DateUtils();
		this.dateupd = dateUtils.parseCalendar(dateupd, "yyyyMMdd HH:mm:ss");
	}



	/**
	 * WMID корреспондента
	 */
	@XmlElement
	@Column
	private Long corrwm;

	/**
	 * остаток после выполнения операции
	 */
	@XmlElement
	@Column
	private Double rest;
	

	public WmOperation() {}

	public WmOperation(Integer id, String pursesrc, String pursedest,
			Double amount, Double comiss, Integer opertype, Integer period,
			Long wminvid, Integer orderid, String desc, Calendar datecrt,
			Calendar dateupd, Long corrwm, Double rest) {
		super();
		this.id = id;
		this.pursesrc = pursesrc;
		this.pursedest = pursedest;
		this.amount = amount;
		this.comiss = comiss;
		this.opertype = opertype;
		this.period = period;
		this.wminvid = wminvid;
		this.orderid = orderid;
		this.desc = desc;
		this.setDatecrt(datecrt);
		this.setDateupd(dateupd);
		this.corrwm = corrwm;
		this.rest = rest;
	}

	public String getPursesrc() {
		return pursesrc;
	}

	public String getPursedest() {
		return pursedest;
	}

	public Double getAmount() {
		return amount;
	}

	public Double getComiss() {
		return comiss;
	}

	public Integer getOpertype() {
		return opertype;
	}

	public Integer getPeriod() {
		return period;
	}

	public Long getWminvid() {
		return wminvid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public String getDesc() {
		return desc;
	}

	public Long getCorrwm() {
		return corrwm;
	}

	public Double getRest() {
		return rest;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPursesrc(String pursesrc) {
		this.pursesrc = pursesrc;
	}

	public void setPursedest(String pursedest) {
		this.pursedest = pursedest;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setComiss(Double comiss) {
		this.comiss = comiss;
	}

	public void setOpertype(Integer opertype) {
		this.opertype = opertype;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public void setWminvid(Long wminvid) {
		this.wminvid = wminvid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setCorrwm(Long corrwm) {
		this.corrwm = corrwm;
	}

	public void setRest(Double rest) {
		this.rest = rest;
	}

	public Integer getId() {
		return id;
	}

	public Purse getPurse() {
		return purse;
	}

	
}
