package com.cabmanagementsystem.repository;

 

import java.util.List;

 

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

 

import com.cabmanagementsystem.entity.Customer;

import com.cabmanagementsystem.entity.TripBooking;

import com.cabmanagementsystem.util.BookingStatus;

import com.cabmanagementsystem.util.CabType;
@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer> {
public List<TripBooking> findAllByBookingStatus(BookingStatus bookingStatus);
public List<TripBooking> findAllByCabType(CabType cabType);
public List<TripBooking> findAllByOrderByStartDateTimeDesc();
public List<TripBooking> findAllByCustomer(Customer customer);
public List<TripBooking> findByCustomerUserName(String userName);
}