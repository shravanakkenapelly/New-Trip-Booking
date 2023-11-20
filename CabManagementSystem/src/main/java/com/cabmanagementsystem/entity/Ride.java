package com.cabmanagementsystem.entity;

 

import java.time.LocalDateTime;

 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

 

import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

 

import com.cabmanagementsystem.util.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ride {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer rideId;

@OneToOne(cascade= CascadeType.ALL)
@NotNull(message = "Customer is required")
private Customer customer;

@OneToOne(cascade= CascadeType.ALL)
@NotNull(message = "Cab is required")
private Cab cab;

@NotBlank(message = "Pickup location is required")
private String pickupLocation;

@NotBlank(message = "Dropoff location is required")
private String dropoffLocation;

private LocalDateTime startTime;
private LocalDateTime endTime;

@Enumerated(EnumType.STRING)
private RideStatus rideStatus;

 public Integer getRideId() {
		return rideId;
	}
public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}

public Customer getCustomer() {
		return customer;
	}

public void setCustomer(Customer customer) {
		this.customer = customer;
	}

public Cab getCab() {
		return cab;
	}
public void setCab(Cab cab) {
		this.cab = cab;
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
public LocalDateTime getStartTime() {
		return startTime;
	}
public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
public LocalDateTime getEndTime() {
		return endTime;
	}
public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
public RideStatus getRideStatus() {
		return rideStatus;
	}
public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}
public Ride() {
		super();
		this.startTime = LocalDateTime.now();
	}
public Ride(Integer rideId, Customer customer, Cab cab, Driver driver, String pickupLocation,
			String dropoffLocation, LocalDateTime startTime, LocalDateTime endTime, RideStatus rideStatus) {
		super();
		this.rideId = rideId;
		this.customer = customer;
		this.cab = cab;
        this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropoffLocation;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rideStatus = rideStatus;
	}
@Override
public String toString() {
		return "Ride [rideId=" + rideId + ", customer=" + customer + ", cab=" + cab + ", pickupLocation="
				+ pickupLocation + ", dropoffLocation=" + dropoffLocation + ", startTime=" + startTime + ", endTime="
				+ endTime + ", rideStatus=" + rideStatus + "]";
	}

}


	
	

 

