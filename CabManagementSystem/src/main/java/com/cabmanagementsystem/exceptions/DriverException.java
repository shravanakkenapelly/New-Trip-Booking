package com.cabmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value=HttpStatus.CONFLICT)
public class DriverException extends Exception{
	
	
		public DriverException(String message) {
			super(message);
		}

		
	}
