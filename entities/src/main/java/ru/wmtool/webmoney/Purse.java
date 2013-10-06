package ru.wmtool.webmoney;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.wmtool.webmoney.xmlinterfaces.history.WmOperation;

/**
 * Класс кошелька в системе webmoney.
 * https://wiki.webmoney.ru/wiki/show/WM-кошелек
 * 
 * В системе WebMoney титульные знаки можно хранить только в кошельках. 
 * Каждый кошелёк имеет номер и обязательно прикреплен к WMID. 
 * Он также, как и WMID, состоит из 12 цифр, но у кошельков перед ними стоит префикс, 
 * обозначающий для каких средств он предназначен. 
 * Например — Z238479008342, R034873236762, E9282374987384, U108374384782 — вот так могут выглядеть 
 * номера кошельков, предназначенных для валют WMZ, WMR, WME и WMU соответственно.
 * 
 * @author null (xrw.null@gmail.com)
 */
@Entity /** метка, означающая что этот класс можно сохранить в БД */
@Table(name = "purses") /** название таблицы, куда будет сохраняться этот класс */
public class Purse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) /* автоматич. генерация */
	private Integer id;

	@Column
	private String number;	

	@Column
	private String type;
	
	@ManyToOne
	private Wmid wmid;
	
	/**
     * Cписок принадлежащих кошельку операций
     */    
	@OneToMany(mappedBy="purse", cascade=CascadeType.ALL)    
	private List<WmOperation> operations;	

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}
	
	public List<WmOperation> getOperations() {
		return operations;
	}

	public void setOperations(List<WmOperation> operations) {
		this.operations = operations;
	}

	public Integer getId() {
		return id;
	}

	public Wmid getWmid() {
		return wmid;
	}

	public void setNumber(String number) {
		//TODO сделать проверку на корректность номера кошелька, то есть соответствие формату
		this.number = number;
	}
}
