package com.cabmanagementsystem.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

 

import com.cabmanagementsystem.entity.Driver;
import com.cabmanagementsystem.exceptions.DriverException;
import com.cabmanagementsystem.exceptions.ResourceNotFoundException;
import com.cabmanagementsystem.repository.DriverRepository;
import com.cabmanagementsystem.serviceimpl.DriverServiceImpl;

 

@SpringBootTest
public class DriverTestCase {

 

    @MockBean
    private DriverRepository driverRepo;

 

    @Autowired
    private DriverServiceImpl driverService;

 

//    @BeforeEach
//    public void setup() {
//        driverService = new DriverServiceImpl(driverRepo);
//    }

 

    @Test
    public void testAddDriver_WhenDriverNotRegistered() throws DriverException {
        // Create a sample driver
        Driver driver = new Driver();
        driver.setLicenseNo("DL12345");

 

        // Mock repository behavior
        when(driverRepo.findByLicenseNo("DL12345")).thenReturn(Optional.empty());
        when(driverRepo.save(driver)).thenReturn(driver);

 

        // Perform the addDriver method
        Driver addedDriver = driverService.addDriver(driver);

 

        // Assertions
        assertNotNull(addedDriver);
        assertEquals("DL12345", addedDriver.getLicenseNo());
    }

 

    @Test
    public void testAddDriver_WhenDriverAlreadyRegistered() {
        // Create a sample driver
        Driver driver = new Driver();
        driver.setLicenseNo("DL12345");

 

        // Mock repository behavior
        when(driverRepo.findByLicenseNo("DL12345")).thenReturn(Optional.of(driver));

 

        // Perform the addDriver method and expect an exception
        assertThrows(DriverException.class, () -> driverService.addDriver(driver));
    }

 

    @Test
    public void testGetDrivers() {
        // Create a sample list of drivers
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());

 

        // Mock repository behavior
        when(driverRepo.findAll()).thenReturn(drivers);

 

        // Perform the getDrivers method
        List<Driver> retrievedDrivers = driverService.getDrivers();

 

        // Assertions
        assertNotNull(retrievedDrivers);
        assertEquals(2, retrievedDrivers.size());
    }

 

    @Test
    public void testUpdateDriver() throws ResourceNotFoundException {
        // Create a sample driver
        Driver driver = new Driver();
        driver.setLicenseNo("DL12345");
        driver.setDriverName("John Doe");

 

        // Create a sample updated driver
        Driver updatedDriver = new Driver();
        updatedDriver.setLicenseNo("DL12345");
        updatedDriver.setDriverName("Jane Doe");

 

        // Mock repository behavior
        List<Driver> driverList = new ArrayList<>();
        driverList.add(driver);
        when(driverRepo.findAll()).thenReturn(driverList);
        when(driverRepo.save(driver)).thenReturn(updatedDriver);

 

        // Perform the updateDriver method
        Driver result = driverService.updateDriver("DL12345", updatedDriver);

 

        // Assertions
        assertNotNull(result);
        assertEquals("Jane Doe", result.getDriverName());
    }

 


 

    @Test
    public void testGetDriverByAvailability() throws ResourceNotFoundException {
        // Create a sample list of drivers
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());

 

        // Mock repository behavior
        when(driverRepo.findByAvailability("Available")).thenReturn(drivers);

 

        // Perform the getDriverByAvailability method
        List<Driver> availableDrivers = driverService.getDriverByAvailability("Available");

 

        // Assertions
        assertNotNull(availableDrivers);
        assertEquals(2, availableDrivers.size());
    }
}