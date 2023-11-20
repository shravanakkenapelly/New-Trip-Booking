package com.cabmanagementsystem.util;

public enum CabType {

	SEDAN, SUV , HATCHBACK;
}
//	    public static CabType fromStringIgnoreCase(String value) {
//	        for (CabType cabType : values()) {
//	            if (cabType.name().equalsIgnoreCase(value)) {
//	                return cabType;
//	            }
//	        }
//	        throw new IllegalArgumentException("Invalid CabType: " + value);
//	    }
//	}
//
//	
//	    SEDAN("Sedan", 4, 2, 10.0),
//	    SUV("SUV", 6, 4, 15.0),
//	    HATCHBACK("Hatchback", 4, 1, 8.0);
//
//	    private final String displayName;
//	    private final int seatingCapacity;
//	    private final int luggageCapacity;
//	    private final double ratePerKm;
//
//	    CabType(String displayName, int seatingCapacity, int luggageCapacity, double ratePerKm) {
//	        this.displayName = displayName;
//	        this.seatingCapacity = seatingCapacity;
//	        this.luggageCapacity = luggageCapacity;
//	        this.ratePerKm = ratePerKm;
//	    }
//
//	    public String getDisplayName() {
//	        return displayName;
//	    }
//
//	    public int getSeatingCapacity() {
//	        return seatingCapacity;
//	    }
//
//	    public int getLuggageCapacity() {
//	        return luggageCapacity;
//	    }
//
//	    public double getRatePerKm() {
//	        return ratePerKm;
//	    }
//
//	    public double calculateCost(double distanceInKm) {
//	        return distanceInKm * ratePerKm;
//	    }