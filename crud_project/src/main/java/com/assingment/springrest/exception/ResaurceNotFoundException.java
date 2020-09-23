package com.assingment.springrest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.SerialVersionUID;

@ResponseStatus
public class ResaurceNotFoundException extends RuntimeException{
		
	private static final long SerialVersionUID = 1L;
	
	public ResaurceNotFoundException(String message) {
		super(message);
	}
	
	public ResaurceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
