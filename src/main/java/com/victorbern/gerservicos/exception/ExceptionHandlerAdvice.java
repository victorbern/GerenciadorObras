package com.victorbern.gerservicos.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandlerAdvice {
	@ExceptionHandler(ResourceException.class)
    public ResponseEntity handleException(ResourceException e) {
        // log exception 
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    } 
}
