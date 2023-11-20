package com.cabmanagementsystem.exceptions;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
	
		@ExceptionHandler(CabException.class)
		public ResponseEntity<?> cabException(CabException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
	
		@ExceptionHandler(AdminException.class)
		public ResponseEntity<?> adminException(AdminException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		
		@ExceptionHandler(DriverException.class)
		public ResponseEntity<?> driverException(DriverException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		
		@ExceptionHandler(CustomerException.class)
		public ResponseEntity<?> customerException(CustomerException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(RideException.class)
		public ResponseEntity<?> rideException(RideException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(TripBookingException.class)
		public ResponseEntity<?> tripBookingException(TripBookingException ex, WebRequest request){
			ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}
}
