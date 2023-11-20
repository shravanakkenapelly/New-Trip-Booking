package com.cabmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.UserNotAvailableException;
import com.cabmanagementsystem.service.CustomerService;
import com.cabmanagementsystem.service.UserLogService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserLogController {
	
	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private UserLogService userlogServ;
	
	@PostMapping("/customer")
    public ResponseEntity<String> registerCustomer(@RequestParam String userName, @RequestParam String password) {
        try {
        	userlogServ.logInCustomer(userName, password);
            return ResponseEntity.ok("Logged in successfully");
        } catch (UserNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
	
	@GetMapping("/customer/logout/{logoutMsg}")
    public String handleLogout(@PathVariable String logoutMsg) {
        Customer customer = userlogServ.logOutCustomer(logoutMsg);
        if ("yes".equalsIgnoreCase(logoutMsg) && customer != null && "Logged out successfully".equals(customer.getLogoutMsg())) {
            return "Logged out successfully";
        }
        return "Failed to logout";
    }
	
	@PostMapping("/driver")
    public ResponseEntity<String> registerDriver(@RequestParam String userName, @RequestParam String password) {
        try {
        	userlogServ.logInDriver(userName, password);
            return ResponseEntity.ok("Logged in successfully");
        } catch (UserNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
	
	@GetMapping("/driver/logout/{logoutMsg}")
    public String handleeLogout(@PathVariable String logoutMsg) {
        Driver driver = userlogServ.logOutDriver(logoutMsg);
        if ("yes".equalsIgnoreCase(logoutMsg) && driver != null && "Logged out successfully".equals(driver.getLogoutMsg())) {
            return "Logged out successfully";
        }
        return "Failed to logout";
    }

}
