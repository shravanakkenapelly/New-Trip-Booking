package com.cabmanagementsystem.serviceimpl;

 

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;

import java.util.Random;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

 

import com.cabmanagementsystem.entity.Cab;

import com.cabmanagementsystem.entity.Customer;

import com.cabmanagementsystem.entity.Ride;

import com.cabmanagementsystem.entity.TripBooking;

import com.cabmanagementsystem.exceptions.TripBookingException;

import com.cabmanagementsystem.repository.CabRepository;

import com.cabmanagementsystem.repository.CustomerRepository;

import com.cabmanagementsystem.repository.RideRepository;

import com.cabmanagementsystem.repository.TripBookingRepository;

import com.cabmanagementsystem.service.TripBookingService;

import com.cabmanagementsystem.util.BookingStatus;

import com.cabmanagementsystem.util.CabType;
@Service
public class TripBookingServiceImpl implements TripBookingService {

    @Autowired
    TripBookingRepository repo;
    @Autowired
    CabRepository cabRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public TripBooking addTripBooking(TripBooking tripBooking, String userName)throws TripBookingException {
   	    Random rand = new Random();
   	    double dist = rand.nextDouble(1000);
        tripBooking.setDistanceInKm((double)dist);
           double ratePerKm =rand.nextDouble(20);
           tripBooking.setRatePerKm((double)ratePerKm);
           tripBooking.setBill(ratePerKm * dist);
           LocalDateTime now = LocalDateTime.now();
           tripBooking.setStartDateTime(now);
           Customer cust = customerRepository.findByUserName(userName).get();
           tripBooking.setCustomer(cust);
           tripBooking.setPickupLocation(tripBooking.getPickupLocation());
           tripBooking.setDropoffLocation(tripBooking.getDropoffLocation());
           tripBooking.setBookingStatus(tripBooking.getBookingStatus());
           tripBooking.setCabType(tripBooking.getCabType());
           return repo.save(tripBooking);
   }

    
    @Override
    public TripBooking updateTripBooking(TripBooking tripBooking, String userName) throws TripBookingException {
	   int id = tripBooking.getTripBookingId();
	   TripBooking OldTripBooking = new TripBooking();
	   OldTripBooking = repo.findById(id).get();
       TripBooking newTripBooking = new TripBooking();
       newTripBooking.setTripBookingId(id);
       
       Random rand = new Random();
  	    double dist = rand.nextDouble(1000);
          tripBooking.setDistanceInKm(dist);
        
          double ratePerKm =rand.nextDouble(20);
          tripBooking.setBill(ratePerKm * dist);


        newTripBooking.setDistanceInKm(dist);
        newTripBooking.setBill(ratePerKm* dist);
        newTripBooking.setStartDateTime(LocalDateTime.now());
        newTripBooking.setBookingStatus(tripBooking.getBookingStatus());
        newTripBooking.setCabType(tripBooking.getCabType());
        newTripBooking.setDropoffLocation(OldTripBooking.getDropoffLocation());
        newTripBooking.setPickupLocation(OldTripBooking.getPickupLocation());
        newTripBooking.setCustomer(OldTripBooking.getCustomer());

        return repo.save(newTripBooking);
}
   @Override
   public String cancelTripBooking(Integer tripBookingId) throws TripBookingException {
      repo.deleteById(tripBookingId);
      return "Booking cancled";
   }
    @Override
    public List<TripBooking> viewAllBookings() throws TripBookingException {
    return repo.findAll();
}
   @Override
   public Optional<TripBooking> viewBookingByBookingId(Integer tripBookingId) throws TripBookingException {
   return repo.findById(tripBookingId);
}
   @Override
   public List<TripBooking> viewBookingByBookingStatus(BookingStatus status) throws TripBookingException {
   return repo.findAllByBookingStatus(status);
}
  @Override
  public List<TripBooking> viewBookingByCabType(CabType cabType) throws TripBookingException {
    return repo.findAllByCabType(cabType);
  }
    @Override
    public List<TripBooking> viewBookingsByDatewiseSortingOrder() throws TripBookingException {
      return repo.findAllByOrderByStartDateTimeDesc();
}
    @Override
     public List<TripBooking> viewBookingByCustomerId(Integer customerId) {
     Customer customer = customerRepository.findById(customerId).get();
     return repo.findAllByCustomer(customer);
 }
    @Override
	public List<TripBooking> viewBookingByUserName(String userName) throws TripBookingException {
	    List<TripBooking> bookings = repo.findByCustomerUserName(userName);

 

	    if (bookings.isEmpty()) {
	        throw new TripBookingException("No bookings found for user: " + userName);
	    }

 

	    return bookings;
	}

}