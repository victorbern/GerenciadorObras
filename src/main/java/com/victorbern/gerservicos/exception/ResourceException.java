package com.victorbern.gerservicos.exception;

import org.springframework.http.HttpStatus;

public class ResourceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	
	public ResourceException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
