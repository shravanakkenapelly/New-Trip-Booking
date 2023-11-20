package com.cabmanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.UserNotAvailableException;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.repository.DriverRepository;
import com.cabmanagementsystem.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private DriverRepository driverRepo;
	
	public Customer logInCustomer(String userName, String password) {
		return customerRepo.findByUserNameAndPassword(userName, password)
				.orElseThrow(() -> new UserNotAvailableException("User not available"));
	}
	
	@Override
	public Customer logOutCustomer(String logoutMsg) {
        // Fetching the current logged-in customer is a simplified representation here. 
        Customer currentCustomer = customerRepo.findById(1).orElse(null); // Assuming ID is 1 for the current customer.

        if ("yes".equalsIgnoreCase(logoutMsg) && currentCustomer != null) {
            currentCustomer.setLogoutMsg("Logged out successfully");
            customerRepo.save(currentCustomer);
        }

        return currentCustomer;
    }
	
	@Override
	public Driver logInDriver(String userName, String password) {
		return driverRepo.findByUserNameAndPassword(userName, password)
				.orElseThrow(() -> new UserNotAvailableException("User not available"));
	}
	
	@Override
	public Driver logOutDriver(String logoutMsg) {
		Driver currentDriver = driverRepo.findById(1).orElse(null); // Assuming ID is 1 for the current customer.

        if ("yes".equalsIgnoreCase(logoutMsg) && currentDriver != null) {
            currentDriver.setLogoutMsg("Logged out successfully");
            driverRepo.save(currentDriver);
        }

        return currentDriver;
	}
	
	
}
