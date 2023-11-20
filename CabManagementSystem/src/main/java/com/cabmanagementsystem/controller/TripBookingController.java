package com.cabmanagementsystem.controller;

import java.util.List;

import java.util.Optional;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

 

import com.cabmanagementsystem.entity.TripBooking;
import com.cabmanagementsystem.exceptions.TripBookingException;
import com.cabmanagementsystem.service.TripBookingService;

import com.cabmanagementsystem.util.BookingStatus;

import com.cabmanagementsystem.util.CabType;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trip")

public class TripBookingController {

    @Autowired
    TripBookingService tripbookingservice;

//    @PostMapping("/addtripbooking/{userId}/{cabId}")
//public TripBooking addtripBooking(@RequestBody TripBooking tripBooking, @PathVariable Integer userId,@PathVariable Integer cabId) throws Throwable {
//       TripBooking t = tripbookingservice.addTripBookings(tripBooking, userId, cabId);
//      return t;
//}

    
    @PostMapping("/addtripbooking/{userName}")
public TripBooking addtripBooking(@RequestBody TripBooking tripBooking, @PathVariable String userName) throws Throwable {
       TripBooking t = tripbookingservice.addTripBooking(tripBooking, userName);
      return t;
}

@PutMapping("/update/{tripBookingId}/{userName}")
public TripBooking updateTripBooking(@PathVariable Integer tripBookingId, @RequestBody TripBooking tripBooking,@PathVariable String userName) throws Throwable {
       tripBooking.setTripBookingId(tripBookingId);
      return tripbookingservice.updateTripBooking(tripBooking, userName);
 }
 @DeleteMapping("/cancel/{tripBookingId}")
  public String cancelTripBooking(@PathVariable Integer tripBookingId) throws Throwable {
  return tripbookingservice.cancelTripBooking(tripBookingId);

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
 @GetMapping("/viewbookingsbydatewisesortingorder")
  public List<TripBooking> viewBookingsByDatewiseSortingOrder() throws Throwable {
  List<TripBooking> vbdso = tripbookingservice.viewBookingsByDatewiseSortingOrder();
  return vbdso;
}
 @GetMapping("/viewBooking/{userName}")
 public List<TripBooking> viewBookingByUserName(@PathVariable String userName) throws TripBookingException {
     List<TripBooking> bookings = tripbookingservice.viewBookingByUserName(userName);
     return bookings;
 }

}