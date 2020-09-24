package com.mastercard.excercise.citiconnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseEntity<Object> handleAll(MissingServletRequestParameterException ex) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, Error.INCORRECT_INPUT.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, Error.VALIDATION_ERROR.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
