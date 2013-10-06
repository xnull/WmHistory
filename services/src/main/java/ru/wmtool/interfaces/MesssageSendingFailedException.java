package ru.wmtool.interfaces;

import ru.wmtool.security.User;

public class MesssageSendingFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5209269719283046424L;
	private User user;
	
	public MesssageSendingFailedException(User user, Exception e) {
		super(e);
		this.user = user;
	}

	
	public User getUser(){
		return user;
	}
}
