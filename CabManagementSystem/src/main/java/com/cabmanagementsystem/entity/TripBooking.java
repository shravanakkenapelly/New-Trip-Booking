package com.cabmanagementsystem.entity;

 

import java.time.LocalDateTime;

 

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

 

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

 

import com.cabmanagementsystem.util.CabType;
import com.fasterxml.jackson.annotation.JsonIgnore;

 

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import com.cabmanagementsystem.util.BookingStatus;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class TripBooking {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tripBookingId;

	@OneToOne
	private Customer customer;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Cab type cannot be null")
	private CabType cabType;

	@NotBlank(message = "Pickup location cannot be blank")
    @Size(max = 255, message = "Pickup location should not exceed 255 characters")
	private String pickupLocation;

	@NotBlank(message = "Dropoff location cannot be blank")
    @Size(max = 255, message = "Dropoff location should not exceed 255 characters")
	private String dropoffLocation;

	@NotNull(message = "Start date time cannot be null")
	private LocalDateTime startDateTime;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Booking status cannot be null")
	private BookingStatus bookingStatus;

	// Could be generated randomly
	@Positive(message = "Distance in kilometers must be a positive value")
	private Double distanceInKm;
	
	@Positive(message = "Distance in kilometers must be a positive value")
	private Double ratePerKm;
	
	
	//@Positive(message = "Bill must be a positive value")
	private Double bill;


	public Integer getTripBookingId() {
		return tripBookingId;
	}
	public void setTripBookingId(Integer tripBookingId) {
		this.tripBookingId = tripBookingId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CabType getCabType() {
		return cabType;
	}
	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDropoffLocation() {
		return dropoffLocation;
	}
	public void setDropoffLocation(String dropoffLocation) {
		this.dropoffLocation = dropoffLocation;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Double getDistanceInKm() {
		return distanceInKm;
	}
	public void setDistanceInKm(Double distanceInKm) {
		this.distanceInKm = distanceInKm;
	}
	public Double getBill() {
		return bill;
	}
	public void setBill(Double bill) {
		this.bill = bill;
	}

	public Double getRatePerKm() {
		return ratePerKm;
	}
	public void setRatePerKm(Double ratePerKm) {
		this.ratePerKm = ratePerKm;
	}
	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TripBooking(Integer tripBookingId, Customer customer,
	 CabType cabType,
			 String pickupLocation,
			  String dropoffLocation,
			LocalDateTime startDateTime,
			 BookingStatus bookingStatus,
			 Double distanceInKm,
			Double ratePerKm,
			Double bill) {
		super();
		this.tripBookingId = tripBookingId;
		this.customer = customer;
		this.cabType = cabType;
		this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropoffLocation;
		this.startDateTime = startDateTime;
		this.bookingStatus = bookingStatus;
		this.distanceInKm = distanceInKm;
		this.ratePerKm = ratePerKm;
		this.bill = bill;
	}
	
}