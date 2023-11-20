package com.cabmanagementsystem.repository;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	Optional<Customer> findByUserNameAndPassword(String userName, String password);
    Optional<Customer> findByMobileNumber(String mobileNumber);
    Optional<Customer> findByUserName(String userName);
    Optional<Customer> findByEmail(String email);
    
	//Optional<Customer> findById(String userName);

 }

