package ru.wmtool.web.register;

public class SameEmailExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -186360461646252596L;
	private String email;
	
	public SameEmailExistException(String message, String email) {
		super(message);
		this.email = email;
	}

	public SameEmailExistException() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
}
