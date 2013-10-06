package ru.wmtool.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;

import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.webmoney.Wmid;

/**
 * Зарегистрированный пользователь системы.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = -5640596889561337427L;
	// стандартный логгер
	private static final Logger log = Logger.getLogger(User.class);

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "username")
	private String username;

	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@OneToOne(mappedBy = "user")
	private RegistrationConfirmCode registrationConfirmCode;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Wmid> wmids;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Authority> authorities;

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public User() {
		authorities = new HashSet<Authority>();
		wmids = new ArrayList<Wmid>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Wmid> getWmids() {
		return wmids;
	}

	public void setWmids(List<Wmid> wmids) {
		this.wmids = wmids;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Проверка, содержит ли юзер вмид
	 * 
	 * @param wmidId
	 *            - номер вмида
	 * @return содержит/не содержит
	 */
	public Boolean containWmid(Integer wmidNumber) {
		for (Wmid currentWmid : wmids) {
			if (currentWmid.getNumber().equals(wmidNumber)) {
				return true;
			}
		}
		log.warn("Юзер " + name + " не содержит wmid " + wmidNumber);
		return false;
	}

	public Wmid getWmidByNumber(Integer wmidNumber) {
		for (Wmid currentWmid : wmids) {
			if (currentWmid.getNumber().equals(wmidNumber)) {
				return currentWmid;
			}
		}
		log.warn("Юзер " + name + " не содержит wmid " + wmidNumber);
		return null;
	}

	public void setRegistrationConfirmCode(
			RegistrationConfirmCode registrationConfirmCode) {
		this.registrationConfirmCode = registrationConfirmCode;
	}

	public RegistrationConfirmCode getRegistrationConfirmCode() {
		return registrationConfirmCode;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", contact="
				+ contact + ", password=" + password + ", enabled=" + enabled
				+ ", wmids=" + wmids + "]";
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + name.hashCode();
		result = 37 * result + username.hashCode();
		result = 37 * result + password.hashCode();
		result = 37 * result + contact.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		User user = (User) obj;
		// проверка поля name
		if ((name != null && user.getName() != null && !(name.equals(user.getName())))
				|| (name != null && user.getName() == null)
				|| (name == null && user.getName() != null)) {
			return false;
		}
		// проверка поля username
		if ((username != null && user.getUsername() != null 
				&&!(username.equals(user.getUsername()))) ||
			(username == null && user.getUsername() != null)
			|| (username != null && user.getUsername() == null)){
				return false;
			}
		// проверка поля password
		if ((password != null && user.getPassword() != null
				&& !(password.equals(user.getPassword()))) ||
			(password == null && user.getPassword() != null)
					|| (password != null && user.getPassword() == null)) {
				return false;
		}
		// проверка поля контакта
		if ((contact != null && user.getContact() != null
				&& !(contact.equals(user.getContact()))) ||
			(contact == null && user.getContact() != null)
					|| (contact != null && user.getContact() == null)) {
				return false;
		}
		return true;
	}
}
