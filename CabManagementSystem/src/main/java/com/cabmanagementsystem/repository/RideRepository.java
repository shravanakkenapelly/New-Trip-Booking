package com.cabmanagementsystem.repository;

 

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 

import com.cabmanagementsystem.entity.Ride;
import com.cabmanagementsystem.util.RideStatus;
@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
	@Query("SELECT r FROM Ride r WHERE r.id = :id")
    Ride getRideById(@Param("id") Long id);
	@Query("SELECT r FROM Ride r WHERE r.customer.userName = :userName")
    List<Ride> findByUserName(@Param("userName") String userName);
   List<Ride> findByPickupLocation(String pickupLocation);
   List<Ride> findByDropoffLocation(String dropoffLocation);
   List<Ride> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
   List<Ride> findByRideStatus(RideStatus rideStatus);
   }