package ru.wmtool.webmoney.xmlinterfaces.history;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Список операций в истории.
 */
@Entity
@Table(name = "operations")
@XmlRootElement(name = "operations")
public class WmOperations implements Serializable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	/**
	 * кол-во платежей, удовлетроряющих запросу
	 */
	@XmlAttribute
	@Column(name = "CNT")
	private Integer cnt;

	@XmlElements(value = { @XmlElement(name = "operation") })
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OPERATIONLIST_ID")
	private List<WmOperation> operationList;

	public Integer getCnt() {
		return cnt;
	}

	public List<WmOperation> getOperationList() {
		return operationList;
	}
}
