package com.cabmanagementsystem.service;

 

import java.util.List;
import java.util.Optional;

import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.User;
import com.cabmanagementsystem.exceptions.CustomerException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;

 

public interface CustomerService {

 

	public Customer registerCustomer(Customer customer) throws CustomerException;

 

	

 

	public List<Customer> viewCustomers();

 

	public Customer updateCustomerByEmail(String email, Customer updatedCustomer) throws ResourceNotFoundException;
	public Optional<Customer> viewCustomerByUserName(String userName) throws CustomerException;







	Customer updateCustomerByUserName(String userName, Customer updatedCustomer) throws ResourceNotFoundException;
	}