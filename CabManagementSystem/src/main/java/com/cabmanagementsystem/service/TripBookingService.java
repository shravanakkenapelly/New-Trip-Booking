package com.cabmanagementsystem.service;

 

import java.util.List;

import java.util.Optional;

 

import com.cabmanagementsystem.entity.TripBooking;

import com.cabmanagementsystem.exceptions.TripBookingException;

import com.cabmanagementsystem.util.BookingStatus;

import com.cabmanagementsystem.util.CabType;

 

public interface TripBookingService {

    //public TripBooking addTripBookings(TripBooking tripBooking, Integer userId, Integer cabId)throws TripBookingException;
	 public TripBooking addTripBooking(TripBooking tripBooking, String userName)throws TripBookingException;
	//public TripBooking updateTripBooking(TripBooking tripBooking, Integer cabId) throws TripBookingException;
	public TripBooking updateTripBooking(TripBooking tripBooking,String userName) throws TripBookingException;
    public String cancelTripBooking(Integer tripBookingId) throws TripBookingException;
    public List<TripBooking> viewAllBookings() throws TripBookingException;
    public Optional<TripBooking> viewBookingByBookingId(Integer tripBookingId) throws TripBookingException;
    public List<TripBooking> viewBookingByCustomerId(Integer customerId);
    public List<TripBooking> viewBookingByBookingStatus(BookingStatus status) throws TripBookingException;
    public List<TripBooking> viewBookingByCabType(CabType cabType) throws TripBookingException;
    public List<TripBooking> viewBookingsByDatewiseSortingOrder() throws TripBookingException;
    public List<TripBooking> viewBookingByUserName(String userName) throws TripBookingException;
}