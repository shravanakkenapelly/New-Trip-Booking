package com.cabmanagementsystem.exceptions;

public class UserNotAvailableException extends RuntimeException {
	public UserNotAvailableException(String message) {
        super(message);
    }
}
