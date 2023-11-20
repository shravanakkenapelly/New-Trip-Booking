package com.cabmanagementsystem.service;

import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;

public interface UserLogService {
	public Customer logInCustomer(String userName, String password);
	public Customer logOutCustomer(String logoutMsg);
	public Driver logInDriver(String userName, String password);
	public Driver logOutDriver(String logoutMsg);
}
