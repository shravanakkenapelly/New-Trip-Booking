package com.cabmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.DriverException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.service.DriverService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/driver")
public class DriverController {

	@Autowired
	private DriverService driverservo;

	@PostMapping("/register")
	public ResponseEntity<Driver> register(@Valid @RequestBody Driver dr) throws DriverException {
		return new ResponseEntity<Driver>(driverservo.addDriver(dr), HttpStatus.CREATED);
	}

	@GetMapping("/getdriver")
	public ResponseEntity<List<Driver>> getDrivers() throws Exception {
		return new ResponseEntity<List<Driver>>(driverservo.getDrivers(), HttpStatus.OK);
	}

	@PutMapping("/update/{LicenseNo}")
	public ResponseEntity<Driver> updateDriver(@PathVariable String LicenseNo, @RequestBody Driver dr)
			throws Exception {
		return new ResponseEntity<Driver>(driverservo.updateDriver(LicenseNo, dr), HttpStatus.OK);
	}

	@GetMapping("/getdriver/{driverAvailability}")
	public ResponseEntity<List<Driver>> getDriverByAvailability(@PathVariable String driverAvailability)
			throws ResourceNotFoundException {
		return new ResponseEntity<List<Driver>>(driverservo.getDriverByAvailability(driverAvailability), HttpStatus.OK);
	}

	@GetMapping("/getdriverbyuser/{userName}")
	public ResponseEntity<Driver> getDriverByUsername(@PathVariable String userName) throws ResourceNotFoundException {
		Optional<Driver> driverOptional = driverservo.getDriverByUserName(userName);
		if (driverOptional != null) {
			return ResponseEntity.ok(driverOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}