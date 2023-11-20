package com.cabmanagementsystem.controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 

import com.cabmanagementsystem.entity.Ride;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.service.RideService;
import com.cabmanagementsystem.util.RideStatus;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rides")
public class RideController {
    private RideService rideService;
    
    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }
    
    @PostMapping("/add/{userId}/{cabId}/{tripBookingId}")
    public ResponseEntity<Ride> addRide(@RequestBody Ride ride,
                                        @PathVariable Integer userId,
                                        @PathVariable Integer cabId,
                                        @PathVariable Integer tripBookingId) throws Throwable {
        Ride addedRide = rideService.addRide(ride, userId, cabId, tripBookingId);
        return new ResponseEntity<>(addedRide, HttpStatus.CREATED);
    }
    
    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideById(@PathVariable Integer rideId) throws Throwable{
        Optional<Ride> ride = rideService.getRideById(rideId);
        return ride.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/customer/{userName}")
    public ResponseEntity<List<Ride>> getRidesByUserName(@PathVariable String userName) throws Throwable{
        List<Ride> rides = rideService.getRidesByUserName(userName);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }


    @GetMapping("/all")
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

    @GetMapping("/time")
    public ResponseEntity<List<Ride>> getRidesByStartTimeBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) throws Throwable{
        List<Ride> rides = rideService.getRidesByStartTimeBetween(startTime, endTime);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    @GetMapping("/status/{rideStatus}")
    public ResponseEntity<List<Ride>> getRidesByRideStatus(@PathVariable RideStatus rideStatus)  throws Throwable{
        List<Ride> rides = rideService.getRidesByRideStatus(rideStatus);
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
}