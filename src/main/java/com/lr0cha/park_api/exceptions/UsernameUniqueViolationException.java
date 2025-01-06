package com.lr0cha.park_api.exceptions;

public class UsernameUniqueViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsernameUniqueViolationException(String msg) {
		super(msg);
	}

}
