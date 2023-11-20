package com.cabmanagementsystem.serviceimpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cabmanagementsystem.entity.Customer;
import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.UserNotAvailableException;
import com.cabmanagementsystem.repository.CustomerRepository;
import com.cabmanagementsystem.repository.DriverRepository;

public class UserLogTestCase {

    @Mock
    private CustomerRepository customerRepo;

    @Mock
    private DriverRepository driverRepo;

    @InjectMocks
    private UserLogServiceImpl userLogService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void logInCustomer_ValidCredentials_ShouldReturnCustomer() {
        Customer customer = new Customer();
        when(customerRepo.findByUserNameAndPassword("testUser", "testPass"))
            .thenReturn(java.util.Optional.of(customer));

        assertEquals(customer, userLogService.logInCustomer("testUser", "testPass"));
    }

    @Test
    public void logInCustomer_InvalidCredentials_ShouldThrowException() {
        when(customerRepo.findByUserNameAndPassword("wrongUser", "wrongPass"))
            .thenReturn(java.util.Optional.empty());

        assertThrows(UserNotAvailableException.class, () -> {
            userLogService.logInCustomer("wrongUser", "wrongPass");
        });
    }

    @Test
    public void logOutCustomer_ValidLogoutMsg_ShouldUpdateAndReturnCustomer() {
        Customer customer = new Customer();
        when(customerRepo.findById(1))
            .thenReturn(java.util.Optional.of(customer));

        assertNotNull(userLogService.logOutCustomer("yes"));
    }

    @Test
    public void logInDriver_ValidCredentials_ShouldReturnDriver() {
        Driver driver = new Driver();
        when(driverRepo.findByUserNameAndPassword("testDriver", "testPass"))
            .thenReturn(java.util.Optional.of(driver));

        assertEquals(driver, userLogService.logInDriver("testDriver", "testPass"));
    }

    @Test
    public void logInDriver_InvalidCredentials_ShouldThrowException() {
        when(driverRepo.findByUserNameAndPassword("wrongDriver", "wrongPass"))
            .thenReturn(java.util.Optional.empty());

        assertThrows(UserNotAvailableException.class, () -> {
            userLogService.logInDriver("wrongDriver", "wrongPass");
        });
    }

    @Test
    public void logOutDriver_ValidLogoutMsg_ShouldUpdateAndReturnDriver() {
        Driver driver = new Driver();
        when(driverRepo.findById(1))
            .thenReturn(java.util.Optional.of(driver));

        assertNotNull(userLogService.logOutDriver("yes"));
    }
}
