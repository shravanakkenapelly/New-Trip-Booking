package com.cabmanagementsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class AdminException extends Exception{
	public AdminException(String message) {
		super(message);
	}
}
