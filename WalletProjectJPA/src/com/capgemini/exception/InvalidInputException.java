package com.capgemini.exception;

public class InvalidInputException extends Exception {

	@Override
	public String toString() {
		return "InvalidInputException";
	}

	public InvalidInputException(String msg) {
		super(msg);
	}
}