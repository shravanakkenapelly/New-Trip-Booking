package com.cabmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import com.cabmanagementsystem.entity.Cab;

import com.cabmanagementsystem.util.CabType;

public interface CabService {

//	public Cab addCab(Cab cab) throws Throwable;
	
	public Cab addCab(Cab cab,String userName) throws Throwable;

	public Cab updateCabByUserName(String userName, Cab updatecab) throws Throwable;

	public List<Cab> viewCabs() throws Throwable;

	public Optional<Cab> viewCabByUserName(String userName) throws Throwable;

	public List<Cab> viewCabByType(CabType cabType) throws Throwable;

	public List<Cab> viewCabByCurrentLocation(String currentLocation) throws Throwable;

	public Optional<Cab> viewCabById(Integer cabId) throws Throwable;

	public List<Cab> viewCabByTypeAndLocation(CabType cabType, String currentLocation) throws Throwable;

	public List<Cab> viewCabByAvailability(String cabAvailability) throws Throwable;

}