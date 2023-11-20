package com.cabmanagementsystem.serviceimpl;
//package com.cabmanagementsystem.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabmanagementsystem.entity.Admin;
import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.exceptions.AdminException;
import com.cabmanagementsystem.exceptions.CabException;
import com.cabmanagementsystem.repository.AdminRepository;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
	private AdminRepository adminRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Override
	public Admin addAdmin(Admin admin) throws AdminException{
		Optional<Admin> findAdmin = adminRepo.findByEmail(admin.getEmail());
		if(findAdmin.isEmpty()) {
			return adminRepo.save(admin);
		}
		else {
			throw new AdminException("Admin is already Registered");
		}
	}

}



