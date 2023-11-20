package com.cabmanagementsystem.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cabmanagementsystem.entity.Admin;
import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User,Integer>
{
	Optional<Admin> findByEmail(String email);
}
