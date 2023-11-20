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

import com.cabmanagementsystem.entity.Admin;
import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.entity.Ride;
import com.cabmanagementsystem.entity.TripBooking;
import com.cabmanagementsystem.exceptions.AdminException;
import com.cabmanagementsystem.exceptions.CabException;
import com.cabmanagementsystem.exceptions.DriverException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.service.AdminService;
import com.cabmanagementsystem.service.CabService;
import com.cabmanagementsystem.service.CustomerService;
import com.cabmanagementsystem.service.DriverService;
import com.cabmanagementsystem.service.RideService;
import com.cabmanagementsystem.service.TripBookingService;
import com.cabmanagementsystem.util.BookingStatus;
import com.cabmanagementsystem.util.CabType;
import com.cabmanagementsystem.util.RideStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private CabService cabServ;
	
	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private DriverService driverservo;
	
	@Autowired
	private RideService rideService;
	
	@Autowired
    TripBookingService tripbookingservice;
	
	//For admin register
	@PostMapping("/register")
	public ResponseEntity<Admin> register(@Valid @RequestBody Admin admin) throws AdminException{

		return new ResponseEntity<Admin>(adminServ.addAdmin(admin),HttpStatus.CREATED);
	}
	
	//For Cab
//	@PostMapping("/registercab")
//	public ResponseEntity<Cab> register(@RequestBody Cab cab) throws Throwable{
//
//		return new ResponseEntity<Cab>(cabServ.addCab(cab),HttpStatus.CREATED);
//	}
	
	@PutMapping("/updatecabbyusername/{userName}")
    public Cab updateCabByUsername(@PathVariable String userName, @RequestBody Cab updatedCab) throws Throwable {
        return cabServ.updateCabByUserName(userName, updatedCab);
    }
	
	@GetMapping("/viewcabs")
	public ResponseEntity<List<Cab>> getCabs() throws Throwable{
		return new ResponseEntity<List<Cab>>(cabServ.viewCabs(),HttpStatus.OK);
	}
	
	@GetMapping("/viewcabbyid/{cabId}")
	public Optional<Cab> getCabById(@PathVariable Integer cabId) throws  Throwable{
		return cabServ.viewCabById(cabId);
	}
	
	@GetMapping("/viewcabsbytypeandcurrentlocation/{cabType}/{currentLocation}")
	public ResponseEntity<List<Cab>> getCabsByTypeAndLocation(
	    @PathVariable("cabType") CabType cabType,
	    @PathVariable("currentLocation") String currentLocation
	) throws Throwable {
	    List<Cab> cabs = cabServ.viewCabByTypeAndLocation(cabType, currentLocation);
	    return new ResponseEntity<>(cabs, HttpStatus.OK);
	}

	@GetMapping("/viewcabsbyavailability/{cabAvailability}")
	public ResponseEntity<List<Cab>> getCabsByAvailability(@PathVariable("cabAvailability") String cabAvailability) throws Throwable {
	    List<Cab> cabs = cabServ.viewCabByAvailability(cabAvailability);
	    return new ResponseEntity<>(cabs, HttpStatus.OK);
	}
	
	//For Customer
	@GetMapping("/viewcustomers")
	public ResponseEntity<List<Customer>> getCustomer()
	{
		return new ResponseEntity<List<Customer>>(customerServ.viewCustomers(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{email}")
    public Customer updateCustomerByEmail(@PathVariable String email, @RequestBody Customer updatedCustomer) throws ResourceNotFoundException {
        return customerServ.updateCustomerByEmail(email, updatedCustomer);
    }
	
	//For Driver 
	@PostMapping("/registerdriver")
	public ResponseEntity<Driver> register(@Valid @RequestBody Driver dr) throws DriverException{
		return new ResponseEntity<Driver>(driverservo.addDriver(dr),HttpStatus.CREATED);	
	}
	
	@GetMapping("/getdriver")
	public ResponseEntity<List<Driver>> getDrivers() throws Exception{
		return new ResponseEntity<List<Driver>>(driverservo.getDrivers(),HttpStatus.OK);
	}
	
	@PutMapping("/update/{LicenseNo}") 
	public ResponseEntity<Driver> updateDriver(@PathVariable String LicenseNo,@RequestBody Driver dr) throws Exception {
		return new ResponseEntity<Driver>(driverservo.updateDriver(LicenseNo, dr), HttpStatus.OK);
	}
	
	@GetMapping("/getdriver/{driverAvailability}") 
	public ResponseEntity<List<Driver>> getDriverByAvailability(@PathVariable String driverAvailability) throws ResourceNotFoundException{
		return new ResponseEntity<List<Driver>>(driverservo.getDriverByAvailability(driverAvailability),HttpStatus.OK);
	}
	
	//For Ride
	@GetMapping("/allride")
    public ResponseEntity<List<Ride>> getAllRides() throws Throwable{
        List<Ride> rides = rideService.getAllRides();
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }
	
	@GetMapping("/pickup/{pickupLocation}")
    public ResponseEntity<List<Ride>> getRidesByPickupLocation(@PathVariable String pickupLocation) throws Throwable{
        List<Ride> rides = rideService.getRidesByPickupLocation(pickupLocation);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @GetMapping("/dropoff/{dropoffLocation}")
    public ResponseEntity<List<Ride>> getRidesByDropoffLocation(@PathVariable String dropoffLocation) throws Throwable{
        List<Ride> rides = rideService.getRidesByDropoffLocation(dropoffLocation);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }
    @GetMapping("/customer/{userName}")
    public ResponseEntity<List<Ride>> getRidesByUserName(@PathVariable String userName) throws Throwable{
        List<Ride> rides = rideService.getRidesByUserName(userName);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }
    @PutMapping("/status/{id}/{rideStatus}")
    public ResponseEntity<String> updateRideStatus(@RequestBody @PathVariable RideStatus rideStatus, @PathVariable Integer id) throws Throwable {
        Optional<Ride> optionalRide = rideService.getRideById(id);
        if (optionalRide.isPresent()) {
            RideStatus updatedStatus = rideService.updateRideStatus(id, rideStatus);
            return ResponseEntity.ok("Ride status updated to " + updatedStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //For TripBooking
    
    @PutMapping("/update/{tripBookingId}/{userName}")
    public TripBooking updateTripBooking(@PathVariable Integer tripBookingId, @RequestBody TripBooking tripBooking,
            @PathVariable String userName) throws Throwable {
        tripBooking.setTripBookingId(tripBookingId);
        return tripbookingservice.updateTripBooking(tripBooking, userName);
    }
    
    @GetMapping("/viewallbookings")

    public List<TripBooking> viewAllBookings() throws Throwable {
        List<TripBooking> v = tripbookingservice.viewAllBookings();
        return v;
    }
    
    @GetMapping("/viewbookingbybookingid/{tripBookingId}")

    public Optional<TripBooking> viewBookingByBookingId(@PathVariable Integer tripBookingId) throws Throwable {
        Optional<TripBooking> vbi = tripbookingservice.viewBookingByBookingId(tripBookingId);
        return vbi;
    }
    
    @GetMapping("/viewbookingbybookingstatus/{status}")

    public List<TripBooking> viewBookingByBookingStatus(@PathVariable("status") BookingStatus status) throws Throwable {
        List<TripBooking> vbs = tripbookingservice.viewBookingByBookingStatus(status);
        return vbs;
    }
    
    @GetMapping("/viewbookingbycabtype/{cabType}")
        public List<TripBooking> viewBookingByCabType(@PathVariable CabType cabType) throws Throwable {
        List<TripBooking> vct = tripbookingservice.viewBookingByCabType(cabType);
        return vct;
    }
    
    @GetMapping("/viewbookingsbydatewisesortingorder")
   public List<TripBooking> viewBookingsByDatewiseSortingOrder() throws Throwable {
        List<TripBooking> vbdso = tripbookingservice.viewBookingsByDatewiseSortingOrder();
        return vbdso;
    }
}
