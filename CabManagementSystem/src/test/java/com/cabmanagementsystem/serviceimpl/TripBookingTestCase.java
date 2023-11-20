//package com.cabmanagementsystem.serviceimpl;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.cabmanagementsystem.entity.Cab;
//import com.cabmanagementsystem.entity.Customer;
//import com.cabmanagementsystem.entity.Ride;
//import com.cabmanagementsystem.entity.TripBooking;
//import com.cabmanagementsystem.exceptions.TripBookingException;
//import com.cabmanagementsystem.repository.CabRepository;
//import com.cabmanagementsystem.repository.CustomerRepository;
//import com.cabmanagementsystem.repository.RideRepository;
//import com.cabmanagementsystem.repository.TripBookingRepository;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class TripBookingTestCase {
//
//    @Mock
//    private TripBookingRepository repo;
//
//    @Mock
//    private CabRepository cabRepository;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private RideRepository rideRepository;
//
//    @InjectMocks
//    private TripBookingServiceImpl service;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddTripBooking() throws TripBookingException {
//        TripBooking tripBooking = new TripBooking();
//
//        Cab cab = new Cab();
//        cab.setRatePerKm(10.0);
//
//        Customer customer = new Customer();
//
//        Ride ride = new Ride();
//        ride.setPickupLocation("Start");
//        ride.setDropoffLocation("End");
//
//        when(cabRepository.findById(anyInt())).thenReturn(Optional.of(cab));
//        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
//        when(rideRepository.findById(anyInt())).thenReturn(Optional.of(ride));
//        when(repo.save(any())).thenReturn(tripBooking);
//
//        TripBooking result = service.addTripBooking(tripBooking, 1, 1, 1);
//        assertNotNull(result);
//    }
//
//
//    @Test
//    public void testCancelTripBooking() throws TripBookingException {
//        doNothing().when(repo).deleteById(anyInt());
//
//        String result = service.cancelTripBooking(1);
//        assertEquals("Booking cancled", result);
//    }
//
//    @Test
//    public void testViewAllBookings() throws TripBookingException {
//        TripBooking tripBooking1 = new TripBooking();
//        TripBooking tripBooking2 = new TripBooking();
//
//        when(repo.findAll()).thenReturn(Arrays.asList(tripBooking1, tripBooking2));
//
//        List<TripBooking> bookings = service.viewAllBookings();
//        assertEquals(2, bookings.size());
//    }
//
//    @Test
//    public void testViewBookingByBookingId() throws TripBookingException {
//        TripBooking tripBooking = new TripBooking();
//        when(repo.findById(anyInt())).thenReturn(Optional.of(tripBooking));
//
//        Optional<TripBooking> result = service.viewBookingByBookingId(1);
//        assertTrue(result.isPresent());
//    }
//
//    // ... other tests for other methods
//
//    @Test
//    public void testViewBookingByCustomerId() {
//        Customer customer = new Customer();
//        TripBooking tripBooking1 = new TripBooking();
//        TripBooking tripBooking2 = new TripBooking();
//
//        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
//        when(repo.findAllByCustomer(any())).thenReturn(Arrays.asList(tripBooking1, tripBooking2));
//
//        List<TripBooking> bookings = service.viewBookingByCustomerId(1);
//        assertEquals(2, bookings.size());
//    }
//}
