package com.cabmanagementsystem.serviceimpl;

 

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.User;
import com.cabmanagementsystem.exceptions.CustomerException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
@Autowired
	CustomerRepository customerRepo;
    @Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
        Optional<Customer> findCustomerByMobileNumber = customerRepo.findByMobileNumber(customer.getMobileNumber());
	    Optional<Customer> findCustomerByUserName = customerRepo.findByUserName(customer.getUserName());
	    Optional<Customer> findCustomerByEmail = customerRepo.findByEmail(customer.getEmail());
        // Check if any of the checks for uniqueness fail
	    if (findCustomerByMobileNumber.isPresent() ||
	        findCustomerByUserName.isPresent() || findCustomerByEmail.isPresent()) {
	        throw new CustomerException("Customer is already registered with the provided information.");
	    }
	    return customerRepo.save(customer);
	}
    @Override
	public Customer updateCustomerByEmail(String email, Customer updatedCustomer) throws ResourceNotFoundException {
	    Optional<Customer> existingCustomerOptional = customerRepo.findByEmail(email);

	    if (existingCustomerOptional.isPresent()) {
	        Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setAddress(updatedCustomer.getAddress());
	        existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
	        existingCustomer.setMobileNumber(updatedCustomer.getMobileNumber());
	        existingCustomer.setPassword(updatedCustomer.getPassword());

	        customerRepo.save(existingCustomer);

	        return existingCustomer;
	    } else {
	        throw new ResourceNotFoundException("Given Email not Match With Our Database");
	    }
	}

	@Override
	public List<Customer> viewCustomers() {
		return customerRepo.findAll();
	}
	@Override
	public Optional<Customer> viewCustomerByUserName(String userName) throws CustomerException {
	    Optional<Customer> customers = customerRepo.findByUserName(userName);
	    if (customers.isEmpty()) {
	        throw new CustomerException("No customers found for user: " + userName);
	    }
	    return customers;
	}
	@Override
    public Customer updateCustomerByUserName(String userName, Customer updatedCustomer) throws ResourceNotFoundException {
        Optional<Customer> existingCustomerOptional = customerRepo.findByUserName(userName);

 

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setUserName(updatedCustomer.getUserName());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
            existingCustomer.setMobileNumber(updatedCustomer.getMobileNumber());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPassword(updatedCustomer.getPassword());

 

            customerRepo.save(existingCustomer);

 

            return existingCustomer;
        } else {
            throw new ResourceNotFoundException("Given Username does not match any customer in our database");
        }
    }

 
}