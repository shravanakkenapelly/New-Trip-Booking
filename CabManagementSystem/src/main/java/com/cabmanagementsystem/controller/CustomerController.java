package com.cabmanagementsystem.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.exceptions.CustomerException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerServ;

	@PostMapping("/register")
	public ResponseEntity<Customer> register(@RequestBody Customer mobilenum) throws CustomerException{

		return new ResponseEntity<Customer>(customerServ.registerCustomer(mobilenum),HttpStatus.CREATED);	
	}

	 @PutMapping("/update/{email}")
	    public Customer updateCustomerByEmail(@PathVariable String email, @RequestBody Customer updatedCustomer) throws ResourceNotFoundException {
	        return customerServ.updateCustomerByEmail(email, updatedCustomer);
	    }
	 
	@GetMapping("/viewcustomers")
	public ResponseEntity<List<Customer>> getCustomer()
	{
		return new ResponseEntity<List<Customer>>(customerServ.viewCustomers(),HttpStatus.OK);
	}
	@GetMapping("viewcustomerbyusername/{userName}")
    public Optional<Customer> viewCustomerByUserName(@PathVariable String userName) throws CustomerException{
        return customerServ.viewCustomerByUserName(userName);
	}
	@PutMapping("/updateCustomer/{userName}")
    public Customer updateCustomerByUserName(@PathVariable String userName,@RequestBody Customer updatedCustomer) throws ResourceNotFoundException {
        return customerServ.updateCustomerByUserName(userName, updatedCustomer);
    }

}