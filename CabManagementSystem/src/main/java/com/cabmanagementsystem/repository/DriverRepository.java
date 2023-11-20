package com.cabmanagementsystem.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

 

    Optional<Driver> findByLicenseNo(String licenseNo);

 

    Optional<Driver> findByUserNameAndPassword(String userName, String password);

 

    @Query("SELECT d FROM Driver d WHERE d.driverAvailability = :driverAvailability")
    List<Driver> findByAvailability(@Param("driverAvailability") String driverAvailability);

 

    Optional<Driver> findByMobileNumber(String mobileNumber);

 

    Optional<Driver> findByUserName(String userName);

 

    Optional<Driver> findByEmail(String email);

 

    //Optional<Driver> findByUserName(String driverName);
}
