package com.credai.sampleapi.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
