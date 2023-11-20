package com.cabmanagementsystem.repository;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.util.CabType;

@Repository
public interface CabRepository extends JpaRepository<Cab,Integer>{

	//Custom methods
	Optional<Cab> findByRegistrationNo(String registrationNo);
    List<Cab> findByCabType(CabType cabType);
    List<Cab> findCabByCurrentLocation(String currentLocation);
    List<Cab> findByCabTypeAndCurrentLocation(CabType cabType, String currentLocation);
    @Query("SELECT c FROM Cab c WHERE c.cabAvailability = :cabAvailability")
	List<Cab> findCabByAvailability(@Param("cabAvailability") String cabAvailability);
    Optional<Cab> findByDriverUserName(String userName);

}
