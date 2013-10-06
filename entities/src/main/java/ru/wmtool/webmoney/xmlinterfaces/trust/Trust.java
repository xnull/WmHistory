package ru.wmtool.webmoney.xmlinterfaces.trust;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс доверия
 * @author null
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "trust")
public class Trust implements Serializable{
	private static final long serialVersionUID = 3655800102097349443L;
	
	@XmlAttribute
	private Integer id;
	
	@XmlAttribute
	private int inv;
	
	@XmlAttribute(name="trans")
	private int transAccess;
	
	@XmlAttribute(name="purse")
	private int purseAccess;
	
	@XmlAttribute(name="transhist")
	private int transhistAccess;
	
	@XmlElement
	private String purse;

	@XmlElement
	private String master;

	@XmlElement
	private int daylimit;

	@XmlElement
	private int dlimit;

	@XmlElement
	private int wlimit;

	@XmlElement
	private int mlimit;

	@XmlElement
	private int dsum;
	
	@XmlElement
	private int wsum;
	
	@XmlElement
	private int msum;
	
	@XmlElement
	private String lastsumdate;

	/**
	 * @return уникальный номер доверия в системе учета WebMoney
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return разрешена(1) или нет(0) идентификатору в теге master 
	 * выписка счетов на доверяемый кошелек purse, принадлежащий ВМ-идентификатору
	 */
	public int getInv() {
		return inv;
	}

	/**
	 * разрешены(1) или нет(0) идентификатору в теге master 
	 * переводы средств по доверию с доверяемого кошелека purse, 
	 * принадлежащего ВМ-идентификатору
	 * @return the trans
	 */
	public int getTransAccess() {
		return transAccess;
	}

	/**
	 * разрешен(1) или нет(0) идентификатору в теге master просмотр баланса 
	 * на доверяемом кошельке purse, принадлежащем ВМ-идентификатору
	 * @return
	 */
	public int getPurseAccess() {
		return purseAccess;
	}

	/**
	 * разрешен(1) или нет(0) идентификатору в теге master просмотр истории операций кошелька purse, 
	 * принадлежащего ВМ-идентификатору
	 * @return the transhist
	 */
	public int getTranshistAccess() {
		return transhistAccess;
	}

	/**
	 * кошелек, принадлежащий идентификатору над которым идентификатору master 
	 * разрешено совершать какие-либо действия
	 * @return 
	 */
	public String getPurse() {
		return purse;
	}

	/**
	 * wmid идентификатор, которому доверено совершать какие либо действия с кошельком purse
	 * @return
	 */
	public String getMaster() {
		return master;
	}

	/**
	 * пороговая сумма, не больше которой может перевести 
	 * с кошелька purse идентификатор master в течение суток.
	 * @return 
	 */
	public int getDaylimit() {
		return daylimit;
	}

	/**
	 * пороговая сумма, не больше которой может перевести с кошелька purse 
	 * указанный идентификатор master в течение текущего дня. 
	 * @return
	 */
	public int getDlimit() {
		return dlimit;
	}

	/**
	 * пороговая сумма, не больше которой может перевести с данного кошелька идентификатор master 
	 * в течение текущей недели.
	 * @return
	 */
	public int getWlimit() {
		return wlimit;
	}

	/**
	 * пороговая сумма, не больше которой может перевести с кошелька purse 
	 * идентификатор master в течение текущего месяца.
	 * @return
	 */
	public int getMlimit() {
		return mlimit;
	}

	/**
	 * общая сумма уже проведенных идентификатором master с кошелька purse операций в тот же день, 
	 * месяц и год, которые указаны в дате провдения последней операции по доверию lastsumdate
	 * @return
	 */
	public int getDsum() {
		return dsum;
	}

	/**
	 * общая сумма уже проведенных идентификатором master с кошелька purse операций 
	 * в ту же неделю и год, которые указаны в дате проведения последней операции 
	 * по доверию lastsumdate
	 * @return
	 */
	public int getWsum() {
		return wsum;
	}

	/**
	 * общая сумма уже проведенных идентификатором master с кошелька purse операций 
	 * в тот же месяц и год, которые указаны в дате проведения последней операции 
	 * по доверию lastsumdate
	 * @return
	 */
	public int getMsum() {
		return msum;
	}

	/**
	 * Дата проведения идентификатором master последнего перевода по доверию с кошелька purse
	 * @return
	 */
	public String getLastsumdate() {
		return lastsumdate;
	}
}
