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

 

import com.cabmanagementsystem.entity.Cab;

 

import com.cabmanagementsystem.service.CabService;
import com.cabmanagementsystem.util.CabType;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cab")
public class CabController {

	@Autowired
	private CabService cabServ;

//	@PostMapping("/registercab")
//	public ResponseEntity<Cab> register(@Valid @RequestBody Cab cab) throws Throwable{
//
//		return new ResponseEntity<Cab>(cabServ.addCab(cab),HttpStatus.CREATED);
//	}
	
	@PostMapping("/registercab/{userName}")
	public ResponseEntity<Cab> register(@Valid @RequestBody Cab cab,@PathVariable String userName) throws Throwable{
		
		return new ResponseEntity<Cab>(cabServ.addCab(cab, userName),HttpStatus.CREATED);
	}

	@PutMapping("/updatecabbyusername/{userName}")
    public Cab updateCabByUsername(@PathVariable String userName, @RequestBody Cab updatedCab) throws Throwable {
        return cabServ.updateCabByUserName(userName, updatedCab);
    }

	@GetMapping("/viewcabs")
	public ResponseEntity<List<Cab>> getCabs() throws Throwable{

		return new ResponseEntity<List<Cab>>(cabServ.viewCabs(),HttpStatus.OK);
	}

	

	@GetMapping("/byusername/{userName}")

	    public ResponseEntity<Optional<Cab>> getCabsByUserName(@PathVariable String userName) throws Throwable {

			Optional<Cab> cabs = cabServ.viewCabByUserName(userName);

	        return new ResponseEntity<>(cabs, HttpStatus.OK);

	    }
	@GetMapping("/viewcabsbytype/{cabType}")
	public ResponseEntity<List<Cab>> getCabsByType(@PathVariable CabType cabType) throws Throwable{

		return new ResponseEntity<List<Cab>>(cabServ.viewCabByType(cabType),HttpStatus.OK);
	}

	@GetMapping("/viewcabsbycurrentlocation/{currentLocation}")
	public ResponseEntity<List<Cab>> getCabsByCurrentLocation(@PathVariable String currentLocation) throws Throwable{

		return new ResponseEntity<List<Cab>>(cabServ.viewCabByCurrentLocation(currentLocation),HttpStatus.OK);
	}

	@GetMapping("/viewCabById/{cabId}")
	public Optional<Cab> getCabById(@PathVariable Integer cabId) throws  Throwable{
		return cabServ.viewCabById(cabId);
	}

	@GetMapping("/viewcabsbytypeandcurrentlocation/{cabType}/{currentLocation}")
	public ResponseEntity<List<Cab>> getCabsByTypeAndLocation(@PathVariable("cabType") CabType cabType,@PathVariable("currentLocation") String currentLocation) throws Throwable {
	    List<Cab> cabs = cabServ.viewCabByTypeAndLocation(cabType, currentLocation);

	    return new ResponseEntity<>(cabs, HttpStatus.OK);
	}

	@GetMapping("/viewcabsbyavailability/{cabAvailability}")
	public ResponseEntity<List<Cab>> getCabsByAvailability(@PathVariable("cabAvailability") String cabAvailability) throws Throwable {
	    List<Cab> cabs = cabServ.viewCabByAvailability(cabAvailability);
	    return new ResponseEntity<>(cabs, HttpStatus.OK);
	}
}