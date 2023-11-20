package com.cabmanagementsystem.entity;

 

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

 

@Entity
public class Admin extends User{

	@NotBlank(message = "Admin name is required")
    @Size(max = 255, message = "Admin name must be less than or equal to 255 characters")
	private String adminName;

 

	public String getAdminName() {
		return adminName;
	}

 

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

 

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminName) {
		super();

		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + "]";
	}


}