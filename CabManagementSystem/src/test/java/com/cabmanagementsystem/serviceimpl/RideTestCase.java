//package com.cabmanagementsystem.serviceimpl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
// 
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
// 
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
// 
//
//import com.cabmanagementsystem.entity.Ride;
//import com.cabmanagementsystem.exceptions.RideException;
//import com.cabmanagementsystem.repository.RideRepository;
//import com.cabmanagementsystem.serviceimpl.RideServiceImpl;
//import com.cabmanagementsystem.util.RideStatus;
//
// 
//
//class RideServiceTestCase {
//@Mock
//    private RideRepository rideRepository;
//@InjectMocks
//    private RideServiceImpl rideService;
//@BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//@Test
//    void testAddRide() throws RideException {
//        Ride ride = new Ride();
//        when(rideRepository.save(any(Ride.class))).thenReturn(ride);
//        Ride savedRide = rideService.addRide(ride);
//        assertNotNull(savedRide);
//        assertEquals(ride, savedRide);
//    }
//@Test
//    void testGetRideById() throws RideException {
//         Ride ride = new Ride();
//        when(rideRepository.findById(1)).thenReturn(Optional.of(ride));
//        Optional<Ride> retrievedRide = rideService.getRideById(1);
//        assertTrue(retrievedRide.isPresent());
//        assertEquals(ride, retrievedRide.get());
//    }
//@Test
// void testGetAllRides() throws RideException {
//         List<Ride> rides = Arrays.asList(new Ride(), new Ride());
//        when(rideRepository.findAll()).thenReturn(rides);
//        List<Ride> retrievedRides = rideService.getAllRides();
//        assertEquals(rides.size(), retrievedRides.size());
//        assertEquals(rides, retrievedRides);
//    }
//
// 
//
//    @Test
//    void testGetRidesByPickupLocation() throws RideException {
//       String pickupLocation = "Location";
//        List<Ride> rides = Arrays.asList(new Ride(), new Ride());
//        when(rideRepository.findByPickupLocation(pickupLocation)).thenReturn(rides);
//        List<Ride> retrievedRides = rideService.getRidesByPickupLocation(pickupLocation);
//        assertEquals(rides.size(), retrievedRides.size());
//        assertEquals(rides, retrievedRides);
//    }
// @Test
//    void testGetRidesByDropoffLocation() throws RideException {
//        String dropoffLocation = "Location";
//        List<Ride> rides = Arrays.asList(new Ride(), new Ride());
//        when(rideRepository.findByDropoffLocation(dropoffLocation)).thenReturn(rides);
//
//        List<Ride> retrievedRides = rideService.getRidesByDropoffLocation(dropoffLocation);
//
//        assertEquals(rides.size(), retrievedRides.size());
//        assertEquals(rides, retrievedRides);
//    }
//
// @Test
//    void testGetRidesByStartTimeBetween() throws RideException {
//       
//        LocalDateTime startTime = LocalDateTime.now();
//        LocalDateTime endTime = startTime.plusHours(1);
//        List<Ride> rides = Arrays.asList(new Ride(), new Ride());
//        when(rideRepository.findByStartTimeBetween(startTime, endTime)).thenReturn(rides);
//
//        List<Ride> retrievedRides = rideService.getRidesByStartTimeBetween(startTime, endTime);
//
//        assertEquals(rides.size(), retrievedRides.size());
//        assertEquals(rides, retrievedRides);
//    }
//
// 
//
//    @Test
//    void testGetRidesByRideStatus() throws RideException {
//        
//        RideStatus rideStatus = RideStatus.ONGOING;
//        List<Ride> rides = Arrays.asList(new Ride(), new Ride());
//        when(rideRepository.findByRideStatus(rideStatus)).thenReturn(rides);
//
//        List<Ride> retrievedRides = rideService.getRidesByRideStatus(rideStatus);
//
//        assertEquals(rides.size(), retrievedRides.size());
//        assertEquals(rides, retrievedRides);
//    }
//
//  @Test
//    void testUpdateRideStatus() throws RideException {
//        Integer rideId = 1;
//        RideStatus newStatus = RideStatus.COMPLETED;
//        Ride existingRide = new Ride();
//        when(rideRepository.findById(rideId)).thenReturn(Optional.of(existingRide));
//
//        RideStatus updatedStatus = rideService.updateRideStatus(rideId, newStatus);
//
//        assertNotNull(updatedStatus);
//        assertEquals(newStatus, updatedStatus);
//    }
//  @Test
//    void testUpdateRideStatusRideNotFound() {
//        Integer rideId = 1;
//        RideStatus newStatus = RideStatus.COMPLETED;
//        when(rideRepository.findById(rideId)).thenReturn(Optional.empty());
//        assertThrows(RideException.class, () -> rideService.updateRideStatus(rideId, newStatus));
//    }
//}