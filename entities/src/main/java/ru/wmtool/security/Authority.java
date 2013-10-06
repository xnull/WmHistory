package ru.wmtool.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4217803714338301028L;
//	public static enum ROLE {ROLE_READER, ROLE_ADMIN, ROLE_USER};
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
//	
	@Id
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@Column(name = "authority")
	private String authority;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
//	public Integer getId() {
//		return id;
//	}
//	
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	public Authority(User user, String authority) {
		super();
		this.user = user;
		this.authority = authority;
	}
	public Authority() {
	}
	@Override
	public String toString() {
		return "Authority [user=" + user + ", authority=" + authority
				+ "]";
	}
}
