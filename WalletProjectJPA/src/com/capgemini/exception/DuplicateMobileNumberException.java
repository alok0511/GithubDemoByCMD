package com.capgemini.exception;

public class DuplicateMobileNumberException extends Exception {

	@Override
	public String toString() {
		return "DuplicateMobileNumberException";
	}
	
	public DuplicateMobileNumberException(String msg) {
		super(msg);
	}
	
	

}
