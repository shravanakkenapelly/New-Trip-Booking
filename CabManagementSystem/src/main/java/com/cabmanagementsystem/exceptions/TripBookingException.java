

package com.cabmanagementsystem.exceptions;

 

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

 

@ResponseStatus(value=HttpStatus.CONFLICT)

public class TripBookingException extends Exception {

 

	public TripBookingException(String message) {

		super(message);

		// TODO Auto-generated constructor stub

	}

 

	

 

}
