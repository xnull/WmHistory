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

import org.apache.log4j.Logger;

import ru.wmtool.security.User;

/**
 * Класс представляющий собой wmid пользователя в сисете вебмани.
 * Идентификатор участника (WMID) представляет собой уникальную 12-тизначную 
 * цифровую последовательность, которая является адресом участника в системе WebMoney
 * https://wiki.webmoney.ru/wiki/show/WMID
 *   
 * @author null (xrw.null@gmail.com)
 */

@Entity
@Table(name = "wmids")
public class Wmid implements Serializable {	
	private static final long serialVersionUID = -4318497087451298439L;
	private static final Logger log = Logger.getLogger(Wmid.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/**
	 * Номер wmid-a пользователя. 
	 */
	@Column(name = "number")
	private Integer number;
	
	@OneToMany(mappedBy="wmid", cascade=CascadeType.ALL)
	private List<Purse> purses;
	
	@ManyToOne
	private User user;

	public Integer getId() {
		return id;
	}
	
	public Integer getNumber() {
		return number;
	}

	public List<Purse> getPurses() {
		return purses;
	}

	public User getUser() {
		return user;
	}

	public void setPurses(List<Purse> purses) {
		this.purses = purses;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * Проверка содержит ли вмид-у данный кошелек
	 * @param wmidId - номер вмида
	 * @return
	 */
	public Boolean containPurse(String purseNumber) {
		for (Purse currentPurse : purses) {
			if (currentPurse.getNumber().equals(purseNumber)) {
				return true;
			}
		}
		log.warn("Wmid " + number + " не содержит кошелька " + purseNumber);
		return false;
	}
	
	public Purse getPurseByNumber(String purseNumber) {
		for (Purse currentPurse : purses) {
			if (currentPurse.getNumber().equals(purseNumber)) {
				return currentPurse;
			}
		}
		log.warn("Wmid " + number + " не содержит кошелька " + purseNumber);
		return null;
	}
}
