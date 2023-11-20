package com.cabmanagementsystem.service;
//package com.cabmanagementsystem.service;


import java.util.List;

import com.cabmanagementsystem.entity.Admin;
import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.exceptions.AdminException;

public interface AdminService {
	
	Admin addAdmin(Admin admin) throws AdminException;
	
//
//	UserDTO registerUser(UserDTO user);
//
//	UserDTO signIn(String userName, String password);
//
//	// use session management accordingly
//	public String signOut();
}
