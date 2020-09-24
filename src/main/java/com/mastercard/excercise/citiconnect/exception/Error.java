package com.mastercard.excercise.citiconnect.exception;

public enum Error {
	
	VALIDATION_ERROR("001","Request validation failed."),
	INCORRECT_INPUT("002","IncorrectInput.");
	
	private String code;
	private String message;

	Error(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
