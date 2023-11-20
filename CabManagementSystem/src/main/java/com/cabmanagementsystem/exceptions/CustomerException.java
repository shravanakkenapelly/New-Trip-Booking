package com.cabmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class CustomerException extends Exception{

			public CustomerException(String message) {
				super(message);
			}
}
