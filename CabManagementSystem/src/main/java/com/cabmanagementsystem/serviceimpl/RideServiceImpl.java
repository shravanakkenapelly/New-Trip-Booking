package com.cabmanagementsystem.serviceimpl;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Ride;
import com.cabmanagementsystem.entity.TripBooking;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.exceptions.RideException;
import com.cabmanagementsystem.exceptions.TripBookingException;
import com.cabmanagementsystem.repository.CabRepository;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.repository.RideRepository;
import com.cabmanagementsystem.repository.TripBookingRepository;
import com.cabmanagementsystem.service.RideService;
import com.cabmanagementsystem.util.RideStatus;
@Service
public class RideServiceImpl implements RideService {
	
	
	@Autowired
    private RideRepository rideRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CabRepository cabRepository;
    @Autowired
    TripBookingRepository tripbookingRepository;

    public Ride addRide(Ride ride, Integer userId, Integer cabId, Integer tripBookingId) throws RideException {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new RideException("Customer not found with ID: " + userId));
       Cab cab = cabRepository.findById(cabId)
                .orElseThrow(() -> new RideException("Cab not found with ID: " + cabId));
        ride.setCustomer(customer);
        ride.setCab(cab);
        ride.setStartTime(LocalDateTime.now());
        TripBooking t1=tripbookingRepository.findById(tripBookingId).get();
        ride.setPickupLocation(t1.getPickupLocation());
        ride.setDropoffLocation(t1.getDropoffLocation());
        return rideRepository.save(ride);
    }
@Override
    public Optional<Ride> getRideById(Integer rideId) throws RideException {
        Optional<Ride> optionalRide = rideRepository.findById(rideId);
         if (optionalRide.isPresent()) {
            return optionalRide;
        } else {

            throw new RideException("Ride not found with id: " + rideId);
        }
    }
@Override
public List<Ride> getRidesByUserName(String userName) throws RideException {
    List<Ride> rides = rideRepository.findByUserName(userName);
     if (rides.isEmpty()) {
        throw new RideException("No rides found for customer: " + userName);
    } else {
        return rides;
    }
}
@Override
public List<Ride> getAllRides() throws RideException {
        List<Ride> rides = (List<Ride>) rideRepository.findAll();
         if (rides != null && !rides.isEmpty()) {
            return rides;
        } else {

            throw new RideException("Failed to retrieve rides or no rides found.");
        }
    }
 @Override
 public List<Ride> getRidesByPickupLocation(String pickupLocation) throws RideException {
        List<Ride> rides = rideRepository.findByPickupLocation(pickupLocation);
         if (rides != null && !rides.isEmpty()) {
            return rides;
        } else {

            throw new RideException("No rides found for pickup location: " + pickupLocation);
        }
    }
@Override
    public List<Ride> getRidesByDropoffLocation(String dropoffLocation) throws RideException {
        List<Ride> rides = rideRepository.findByDropoffLocation(dropoffLocation);
          if (rides != null && !rides.isEmpty()) {
            return rides;
        } else {

            throw new RideException("No rides found for dropoff location: " + dropoffLocation);
        }
} 
@Override
 public List<Ride> getRidesByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime) throws RideException {
 List<Ride> rides = rideRepository.findByStartTimeBetween(startTime, endTime);
          if (rides != null && !rides.isEmpty()) {
            return rides;
        } else {

            throw new RideException("No rides found between the specified start and end times");
        }
    }
@Override
 public List<Ride> getRidesByRideStatus(RideStatus rideStatus) throws RideException {
        List<Ride> rides = rideRepository.findByRideStatus(rideStatus);
         if (rides != null && !rides.isEmpty()) {
            return rides;
        } else {

            throw new RideException("No rides with given ridestatus");
        }
    }
@Override
public RideStatus updateRideStatus(Integer id, RideStatus rideStatus) throws RideException{
        Optional<Ride> optionalRide = rideRepository.findById(id);
        if (optionalRide.isPresent()) {
            Ride ride = optionalRide.get();
            ride.setRideStatus(rideStatus);
            rideRepository.save(ride);
            return rideStatus;
        } else {
            throw new RideException("Ride not found with id: " + id);
        }
    }

    }	
