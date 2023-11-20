package com.cabmanagementsystem.serviceimpl;

import java.util.List;

import java.util.Optional;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.CabException;

import com.cabmanagementsystem.repository.CabRepository;
import com.cabmanagementsystem.repository.DriverRepository;
import com.cabmanagementsystem.service.CabService;
import com.cabmanagementsystem.util.CabType;

@Service
public class CabServiceImpl implements CabService {
	@Autowired
	private CabRepository cabRepo;
	@Autowired
	private DriverRepository driverRepo;

//	@Override
//	public Cab addCab(Cab cab) throws Throwable {
//		// Fetch the Driver entity by userId
//		Optional<Driver> driverOptional = driverRepo.findById(cab.getDriver().getUserId());
//		if (driverOptional.isEmpty()) {
//			throw new CabException("Driver not found with userId: " + cab.getDriver().getUserId());
//		}
//		// Set the fetched Driver entity in the Cab
//		cab.setDriver(driverOptional.get());
//		Optional<Cab> findCab = cabRepo.findByRegistrationNo(cab.getRegistrationNo());
//		if (findCab.isEmpty()) {
//			return cabRepo.save(cab);
//		} else {
//			throw new CabException("Cab is already Registered");
//		}
//	}

	@Override
	public Cab addCab(Cab cab, String userName) throws Throwable {
	    // Fetch the Driver entity by username
	    Optional<Driver> driverOptional = driverRepo.findByUserName(userName);

	    if (driverOptional.isEmpty()) {
	        throw new CabException("Driver not found with username: " + userName);
	    }

	    // Set the fetched Driver entity in the Cab
	    cab.setDriver(driverOptional.get());

	    Optional<Cab> findCab = cabRepo.findByRegistrationNo(cab.getRegistrationNo());

	    if (findCab.isEmpty()) {
	        return cabRepo.save(cab);
	    } else {
	        throw new CabException("Cab is already registered");
	    }
	}
	
//	@Override
//	public Cab updateCabById(Integer cabId, Cab updateCab) throws Throwable {
//		Supplier<CabException> s1 = () -> new CabException("Cab with ID : " + cabId + " does not exist ");
//		Cab existingCab = cabRepo.findById(cabId).orElseThrow(s1);
//		// Update the properties of the existing cab with the values from updateCab
//
//		existingCab.setCabAvailability(updateCab.getCabAvailability());
//		existingCab.setCurrentLocation(updateCab.getCurrentLocation());
//
//		// Save the updated cab
//		Cab savedCab = cabRepo.save(existingCab);
//		return savedCab;
//	}
	
	@Override
	public Cab updateCabByUserName(String userName, Cab updateCab) throws Throwable {
	    Supplier<CabException> cabNotFoundSupplier = () -> new CabException("Cab with username: " + userName + " does not exist");

	 

	    // Find the cab by username
	    Cab existingCab = cabRepo.findByDriverUserName(userName).orElseThrow(cabNotFoundSupplier);

	 

	    // Update the properties of the existing cab with the values from updateCab

	    existingCab.setCabAvailability(updateCab.getCabAvailability());
	    existingCab.setCurrentLocation(updateCab.getCurrentLocation());

	 

	    // Update the properties of the driver associated with the cab
	    Driver driver = existingCab.getDriver();
	    driver.setAddress(updateCab.getDriver().getAddress());
	    driver.setDriverName(updateCab.getDriver().getDriverName());
	    driver.setLicenseNo(updateCab.getDriver().getLicenseNo());
	    driver.setEmail(updateCab.getDriver().getEmail());
	    driver.setMobileNumber(updateCab.getDriver().getMobileNumber());
	    driver.setPassword(updateCab.getDriver().getPassword());
	    driver.setUserName(updateCab.getDriver().getUserName());
	    driver.setDriverAvailability(updateCab.getDriver().getDriverAvailability());

	 

	    // Save the updated cab
	    Cab savedCab = cabRepo.save(existingCab);

	 

	    return savedCab;
	}

	@Override
	public List<Cab> viewCabs() throws Throwable {
		List<Cab> cabs = cabRepo.findAll();
		if (cabs.isEmpty()) {
			throw new CabException("No cabs found");
		} else {
			return cabs;
		}
	}

	@Override
	public Optional<Cab> viewCabByUserName(String userName) throws CabException {
		Optional<Cab> cabs = cabRepo.findByDriverUserName(userName);
		if (cabs.isEmpty()) {
			throw new CabException("No cabs found for user: " + userName);
		}
		return cabs;
	}

	@Override
	public List<Cab> viewCabByType(CabType cabType) throws CabException {
		List<Cab> cabs = cabRepo.findByCabType(cabType);
		if (cabs.isEmpty()) {
			throw new CabException("No cabs found for the specified type" + cabType);
		}
		return cabs;
	}

	@Override
	public List<Cab> viewCabByCurrentLocation(String currentLocation) throws Throwable {
		List<Cab> cabs = cabRepo.findCabByCurrentLocation(currentLocation);
		if (!cabs.isEmpty()) {
			return cabs;
		} else {
			throw new CabException("Cabs Not Found in CurrentLocation: " + currentLocation);
		}
	}

	@Override
	public Optional<Cab> viewCabById(Integer cabId) throws Throwable {
		Optional<Cab> cabs = cabRepo.findById(cabId);
		if (!cabs.isEmpty()) {
			return cabs;
		} else {
			throw new CabException("Cab Not Found with CabId: " + cabId);
		}
	}

	@Override
	public List<Cab> viewCabByTypeAndLocation(CabType cabType, String currentLocation) throws Throwable {
		List<Cab> cabs = cabRepo.findByCabTypeAndCurrentLocation(cabType, currentLocation);
		if (cabs.isEmpty()) {
			throw new CabException("No cabs found with CabType: " + cabType + " and Location: " + currentLocation);
		}
		return cabs;
	}

	@Override
	public List<Cab> viewCabByAvailability(String cabAvailability) throws Throwable {
		List<Cab> cabs = cabRepo.findCabByAvailability(cabAvailability);
		if (cabs.isEmpty()) {
			throw new CabException("No Cabs Are Available Now");
		}
		return cabs;
	}

}