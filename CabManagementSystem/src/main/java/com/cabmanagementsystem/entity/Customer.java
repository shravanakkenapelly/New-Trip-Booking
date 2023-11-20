package com.cabmanagementsystem.entity;

 

import javax.persistence.Entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

 


@Entity
public class Customer extends User{

 

	@NotNull(message = "Customer name is required")
    @NotEmpty(message = "Customer name should not be empty")
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String customerName) {
		super();

		this.customerName = customerName;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + "]";
	}


	public void setLoggedIn(boolean b) {
		// TODO Auto-generated method stub

	}

 

}