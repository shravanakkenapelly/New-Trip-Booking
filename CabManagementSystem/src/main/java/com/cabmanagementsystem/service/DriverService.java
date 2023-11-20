package com.cabmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.DriverException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;

public interface DriverService {
	public Driver addDriver(Driver dr) throws DriverException;

	public List<Driver> getDrivers();

	public Driver updateDriver(String licenseNo, Driver dr) throws ResourceNotFoundException;

	public List<Driver> getDriverByAvailability(String driverAvailability) throws ResourceNotFoundException;

	public Optional<Driver> getDriverByUserName(String userName) throws ResourceNotFoundException;
}