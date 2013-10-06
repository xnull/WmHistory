package ru.wmtool.security.registration;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ru.wmtool.security.User;

/***********************************************************************
 * Module:  RegistrationConfirmCode.java
 * Author:  Maxim
 * Purpose: Defines the Class RegistrationConfirmCode
 ***********************************************************************/

/**
 * Регистрационный код подтверждения.
 * 
 * @pdOid 6cbb1486-46fe-4714-b3a1-80480a94e1a3
 */
@Entity
@Table(name = "registration_confirm_code")
public class RegistrationConfirmCode implements Serializable {
	private static final long serialVersionUID = -4453068194042216981L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	/** @pdOid 728903cf-b6cf-403d-83cf-556afcf73af7 */
	@OneToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;

	/** @pdOid 158e06f3-6b3c-41ba-8322-77be258fa811 */
	@Column(name = "confirm_code", nullable = false, unique = true)
	private String confirmCode;
	
	@Column(name = "registration_date")
	private Timestamp registrationDate;
	
	@Column(name = "used")
	private Boolean used;

	/** @pdOid 6e9e5ae0-b8b0-4ae6-af45-9fc181ffbbfb */
	public String getConfirmCode() {
		return confirmCode;
	}

	/**
	 * @param newConfirmCode
	 * @pdOid 0917e969-42e0-408a-aeb3-917c8d3f6f32
	 */
	public void setConfirmCode(String newConfirmCode) {
		confirmCode = newConfirmCode;
	}

	/** @pdOid 64ceccaa-2beb-4f64-a912-9558f0ce7d4e */
	public User getUser() {
		return user;
	}

	/**
	 * @param newUser
	 * @pdOid 813d89d7-2689-4175-96c2-63c973d07bc6
	 */
	public void setUser(User newUser) {
		user = newUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}
	
	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	/** @pdOid 8b9f1ced-79f5-49ff-a918-831e0d52e56f */
	public RegistrationConfirmCode() {
	}

	/**
	 * @param user
	 * @param confirmCode
	 * @pdOid 27171f3c-b18a-47da-bc0f-4e7dc95b7d63
	 */
	public RegistrationConfirmCode(User user, String confirmCode, Timestamp registrationDate) {
		this.user = user;
		this.confirmCode = confirmCode;
		this.registrationDate = registrationDate;
	}
	
	public void setUsed(Boolean used) {
		this.used = used;
	}
	
	public Boolean getUsed() {
		return used;
	}

	@Override
	public String toString() {
		return "RegistrationConfirmCode [id=" + id + ", user=" + user
				+ ", confirmCode=" + confirmCode + ", registrationDate="
				+ registrationDate + "]";
	}

}