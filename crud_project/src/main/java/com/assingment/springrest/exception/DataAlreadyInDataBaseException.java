package com.assingment.springrest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DataAlreadyInDataBaseException extends RuntimeException{
		
	private static final long SerialVersionUID = 2L;
	
	public DataAlreadyInDataBaseException(String message) {
		super(message);
	}
	
	public DataAlreadyInDataBaseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
