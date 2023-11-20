package com.cabmanagementsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class User {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer userId;

	    @NotBlank(message = "Username cannot be blank")
	    @Size(min = 3, max = 50, message = "Username should be between 3 and 50 characters")
	    private String userName;

	    @NotBlank(message = "Password cannot be blank")
	    @Size(min = 8, message = "Password should be at least 8 characters")
	    private String password;

	    @NotBlank(message = "Address cannot be blank")
	    private String address;

	    @NotBlank(message = "Mobile number cannot be blank")
	    @Size(min = 10, max = 15, message = "Mobile number should be between 10 and 15 digits")
	    private String mobileNumber;

	    @NotBlank(message = "Email cannot be blank")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotNull(message = "Roles cannot be null")
	    private String roles;

	    private String logoutMsg;

   public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLogoutMsg() {
		return logoutMsg;
	}
	public void setLogoutMsg(String logoutMsg) {
		this.logoutMsg = logoutMsg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer userId, String userName, String password, String address, String mobileNumber, String email,
			String roles, String logoutMsg) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.roles = roles;
		this.logoutMsg= logoutMsg;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", roles=" + roles + ", logoutMsg="
				+ logoutMsg + "]";
	}

 


 

 

	
}