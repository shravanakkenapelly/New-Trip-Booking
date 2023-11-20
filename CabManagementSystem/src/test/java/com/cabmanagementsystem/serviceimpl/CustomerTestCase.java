package com.cabmanagementsystem.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

 

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.exceptions.CustomerException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.serviceimpl.CustomerServiceImpl;

 

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerTestCase {

 

    @Mock
    private CustomerRepository customerRepo;

 

    @InjectMocks
    private CustomerServiceImpl customerService;

 

    private Customer testCustomer;

 

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer();
        testCustomer.setCustomerName("Test Customer");
        testCustomer.setEmail("test@example.com");
        testCustomer.setMobileNumber("1234567890");
        testCustomer.setPassword("testpassword");
        testCustomer.setAddress("Test Address");
    }

 

    @Test

    public void testRegisterCustomerSuccess() throws CustomerException {
        // Arrange
        when(customerRepo.findByMobileNumber(testCustomer.getMobileNumber())).thenReturn(Optional.empty());
        when(customerRepo.save(testCustomer)).thenReturn(testCustomer);

 

        // Act
        Customer registeredCustomer = customerService.registerCustomer(testCustomer);

 

        // Assert
        assertNotNull(registeredCustomer);
        assertEquals(testCustomer.getCustomerName(), registeredCustomer.getCustomerName());
        assertEquals(testCustomer.getEmail(), registeredCustomer.getEmail());
        assertEquals(testCustomer.getMobileNumber(), registeredCustomer.getMobileNumber());
        assertEquals(testCustomer.getPassword(), registeredCustomer.getPassword());
        assertEquals(testCustomer.getAddress(), registeredCustomer.getAddress());
    }


 

 

//    @Test
//
//    public void testUpdateCustomerByEmailSuccess() throws ResourceNotFoundException {
//        // Arrange
//        when(customerRepo.findByEmail(testCustomer.getEmail())).thenReturn(testCustomer);
//
// 
//
//        Customer updatedCustomer = new Customer();
//        updatedCustomer.setCustomerName("Updated Customer");
//        updatedCustomer.setMobileNumber("9876543210");
//        updatedCustomer.setPassword("updatedpassword");
//        updatedCustomer.setAddress("Updated Address");
//
// 
//
//        // Act
//        Customer result = customerService.updateCustomerByEmail(testCustomer.getEmail(), updatedCustomer);
//
// 
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(updatedCustomer.getCustomerName(), result.getCustomerName());
//        assertEquals(updatedCustomer.getMobileNumber(), result.getMobileNumber());
//        assertEquals(updatedCustomer.getPassword(), result.getPassword());
//        assertEquals(updatedCustomer.getAddress(), result.getAddress());
//    }
//
// 
    
    @Test
    public void testUpdateCustomerByEmailFailure() {
        // Arrange
        when(customerRepo.findByEmail(testCustomer.getEmail())).thenReturn(null);

 

        Customer updatedCustomer = new Customer();
        updatedCustomer.setCustomerName("Updated Customer");
        updatedCustomer.setMobileNumber("9876543210");
        updatedCustomer.setPassword("updatedpassword");
        updatedCustomer.setAddress("Updated Address");

 

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> customerService.updateCustomerByEmail(testCustomer.getEmail(), updatedCustomer));
    }

 

    @Test
    public void testViewCustomers() {
        // Arrange
        List<Customer> customerList = new ArrayList<>();
        customerList.add(testCustomer);

 

        when(customerRepo.findAll()).thenReturn(customerList);

 

        // Act
        List<Customer> result = customerService.viewCustomers();

 

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCustomer, result.get(0));
    }
}