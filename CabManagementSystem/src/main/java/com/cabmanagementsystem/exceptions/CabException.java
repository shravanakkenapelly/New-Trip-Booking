package com.cabmanagementsystem.exceptions;

 

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

 

@ResponseStatus(value=HttpStatus.CONFLICT)
public class CabException extends Exception{

 

	public CabException(String message) {
		super(message);
	}

 

	
}
