package com.cabmanagementsystem.exceptions;

 

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

 

@ResponseStatus(value=HttpStatus.CONFLICT)
public class RideException extends Exception{

 

	public RideException(String message) {
		super(message);

	}

 

}