package com.mastercard.excercise.citiconnect.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -8552533779515411599L;

	private HttpStatus httpStatus;

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(String message, HttpStatus httpStatus, Throwable cause) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public ApiException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
