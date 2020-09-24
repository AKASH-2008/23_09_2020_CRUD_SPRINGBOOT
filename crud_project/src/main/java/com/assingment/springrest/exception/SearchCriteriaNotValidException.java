package com.assingment.springrest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class SearchCriteriaNotValidException extends RuntimeException{
	
	private static final long SerialVersionUID = 2L;
	
		public SearchCriteriaNotValidException(String message) {
			super(message);
		}
		
		public SearchCriteriaNotValidException(String message, Throwable throwable) {
			super(message, throwable);
		}
	
	}

