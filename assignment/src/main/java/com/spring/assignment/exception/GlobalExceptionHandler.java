package com.spring.assignment.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		Map<String, Object> body = new HashMap<>();
		HttpStatus status;
		String errorCode;
		System.out.println(ex.getClass().getSimpleName());
		switch (ex.getClass().getSimpleName()) {
		case "NotFound":
			status = HttpStatus.NOT_FOUND;
			errorCode = "NOT_FOUND";
			break;
		case "ResourceNotFoundException":
			status = HttpStatus.NOT_FOUND; 
			errorCode = "NOT_FOUND";
			break;
		case "IllegalArgumentException":
			status = HttpStatus.BAD_REQUEST;
			errorCode = "BAD_REQUEST";
			break;
		case "NullPointerException":
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			errorCode = "NULL_POINTER";
			break;
		default:
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			errorCode = "INTERNAL_ERROR";
		}
		body.put("timestamp", LocalDateTime.now());
		body.put("error", errorCode);
		body.put("message", ex.getMessage().toString());
		return new ResponseEntity<>(body, status);
	}
}
