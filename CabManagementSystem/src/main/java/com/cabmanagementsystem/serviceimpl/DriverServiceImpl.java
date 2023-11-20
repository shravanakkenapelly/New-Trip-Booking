package com.cabmanagementsystem.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.DriverException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.repository.DriverRepository;
import com.cabmanagementsystem.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverRepository driverRepo;

	@Override
	public Driver addDriver(Driver dr) throws DriverException {
		Optional<Driver> findDriverByLicenseNo = driverRepo.findByLicenseNo(dr.getLicenseNo());
		Optional<Driver> findDriverByMobileNumber = driverRepo.findByMobileNumber(dr.getMobileNumber());
		Optional<Driver> findDriverByUserName = driverRepo.findByUserName(dr.getUserName());
		Optional<Driver> findDriverByEmail = driverRepo.findByEmail(dr.getEmail());
		if (findDriverByLicenseNo.isPresent() || findDriverByMobileNumber.isPresent()
				|| findDriverByUserName.isPresent() || findDriverByEmail.isPresent()) {
			throw new DriverException("Driver is already registered with the provided information.");
		}
		return driverRepo.save(dr);
	}

	public List<Driver> getDrivers() {
		return driverRepo.findAll();
	}

	@Override
	public Driver updateDriver(String licenseNo, Driver udriver) throws ResourceNotFoundException {
		List<Driver> dl = driverRepo.findAll();
		boolean driverFound = false;

		for (Driver k : dl) {
			if (k.getLicenseNo().equals(licenseNo)) {
				k.setUserName(udriver.getUserName());
				k.setDriverAvailability(udriver.getDriverAvailability());
				k.setDriverName(udriver.getDriverName());
				k.setPassword(udriver.getPassword());
				k.setAddress(udriver.getAddress());
				k.setMobileNumber(udriver.getMobileNumber());
				k.setEmail(udriver.getEmail());

				driverRepo.save(k);
				driverFound = true;
				break; // Exit the loop since we found the driver
			}
		}

		if (!driverFound) {
			throw new ResourceNotFoundException("Given License no did not match with our database");
		}

		return null;
	}

	@Override
	public List<Driver> getDriverByAvailability(String driverAvailability) throws ResourceNotFoundException {
		List<Driver> list = driverRepo.findByAvailability(driverAvailability);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new ResourceNotFoundException("Please Enter Valid Availability Status");
		}
	}

	@Override
	public Optional<Driver> getDriverByUserName(String userName) throws ResourceNotFoundException {
		return driverRepo.findByUserName(userName);
	}

}