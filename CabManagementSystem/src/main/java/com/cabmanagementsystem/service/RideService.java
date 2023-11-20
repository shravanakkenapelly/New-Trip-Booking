package com.cabmanagementsystem.service;

 

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

 

import com.cabmanagementsystem.entity.Ride;
import com.cabmanagementsystem.exceptions.RideException;
import com.cabmanagementsystem.util.RideStatus;

public interface RideService {
    Optional<Ride> getRideById(Integer rideId) throws RideException;
    List<Ride> getRidesByUserName(String userName) throws RideException;
    List<Ride> getAllRides() throws RideException;
    List<Ride> getRidesByPickupLocation(String pickupLocation) throws RideException;
    List<Ride> getRidesByDropoffLocation(String dropoffLocation) throws RideException;
    List<Ride> getRidesByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime)throws RideException;
    List<Ride> getRidesByRideStatus(RideStatus rideStatus) throws RideException;
    Ride addRide(Ride ride,Integer userId, Integer cabId, Integer tripBookingId)throws RideException;
	RideStatus updateRideStatus(Integer id, RideStatus rideStatus) throws RideException;
}