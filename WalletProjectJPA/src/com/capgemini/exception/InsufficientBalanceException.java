package com.capgemini.exception;

public class InsufficientBalanceException extends Exception {
	
	@Override
	public String toString() {
		return "InsufficientBalanceException";
	}

	public InsufficientBalanceException(String msg) {
		super(msg);
	}
}

