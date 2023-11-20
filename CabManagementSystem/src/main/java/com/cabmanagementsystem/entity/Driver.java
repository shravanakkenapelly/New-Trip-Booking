package com.cabmanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "driver")
public class Driver extends User {

	@NotNull(message = "Driver name is required")
	@NotEmpty(message = "Driver name should not be empty")
	private String driverName;

	@NotNull(message = "License number is required")
	@NotEmpty(message = "License number should not be empty")
	@Pattern(regexp = "^[A-Za-z]{2}\\d{5,19}$", message = "License number should consist of alphanumeric characters only")
	private String licenseNo;

	private String driverAvailability;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getDriverAvailability() {
		return driverAvailability;
	}

	public void setDriverAvailability(String driverAvailability) {
		this.driverAvailability = driverAvailability;
	}

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Driver(String driverName, String licenseNo, String driverAvailability) {
		super();

		this.driverName = driverName;
		this.licenseNo = licenseNo;
		this.driverAvailability = driverAvailability;
	}

	@Override
	public String toString() {
		return "Driver [driverName=" + driverName + ", licenseNo=" + licenseNo + ", driverAvailability="
				+ driverAvailability + "]";
	}

}