package com.cabmanagementsystem.entity;

 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 

import com.cabmanagementsystem.util.CabType;

 

 

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Cab {

 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cabId;

	@Enumerated(EnumType.STRING)
	private CabType cabType;

	@OneToOne(cascade= CascadeType.ALL)
	private Driver driver;

	

	@NotBlank(message = "Registration number is required")
    @Size(max = 255, message = "Registration number must be less than or equal to 255 characters")
	private String registrationNo;

	@NotBlank(message = "Cab availability is required")
	private String cabAvailability;


	@NotBlank(message = "Current location is required")
    @Size(max = 255, message = "Current location must be less than or equal to 255 characters")
	private String currentLocation;

	public Integer getCabId() {
		return cabId;
	}
	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}
	public CabType getCabType() {
		return cabType;
	}
	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getCabAvailability() {
		return cabAvailability;
	}
	public void setCabAvailability(String cabAvailability) {
		this.cabAvailability = cabAvailability;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	public Cab() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cab(Integer cabId, CabType cabType, Driver driver,
			@NotBlank(message = "Registration number is required") @Size(max = 255, message = "Registration number must be less than or equal to 255 characters") String registrationNo,
			@NotBlank(message = "Cab availability is required") String cabAvailability,
			@NotBlank(message = "Current location is required") @Size(max = 255, message = "Current location must be less than or equal to 255 characters") String currentLocation) {
		super();
		this.cabId = cabId;
		this.cabType = cabType;
		this.driver = driver;
		this.registrationNo = registrationNo;
		this.cabAvailability = cabAvailability;
		this.currentLocation = currentLocation;
	}
	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", cabType=" + cabType + ", driver=" + driver + ", registrationNo="
				+ registrationNo + ", cabAvailability=" + cabAvailability + ", currentLocation=" + currentLocation
				+ "]";
	}
	
}