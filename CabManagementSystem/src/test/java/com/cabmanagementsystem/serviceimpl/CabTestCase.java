package com.cabmanagementsystem.serviceimpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cabmanagementsystem.entity.Cab;
import com.cabmanagementsystem.exceptions.CabException;
import com.cabmanagementsystem.repository.CabRepository;
import com.cabmanagementsystem.util.CabType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CabTestCase {

    @Mock
    private CabRepository cabRepo;

    @InjectMocks
    private CabServiceImpl cabService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ... [Other test methods]

    @Test
    public void viewCabById_CabExists_ShouldReturnCab() throws Throwable {
        Cab cab = new Cab();
        when(cabRepo.findById(1)).thenReturn(Optional.of(cab));

        Optional<Cab> resultCab = cabService.viewCabById(1);

        assertTrue(resultCab.isPresent());
        assertEquals(cab, resultCab.get());
    }

    @Test
    public void viewCabById_CabDoesNotExist_ShouldThrowException() {
        when(cabRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(CabException.class, () -> {
            cabService.viewCabById(1);
        });
    }

    @Test
    public void viewCabByTypeAndLocation_CabsExist_ShouldReturnListOfCabs() throws Throwable {
        List<Cab> cabs = Arrays.asList(new Cab(), new Cab());
        when(cabRepo.findByCabTypeAndCurrentLocation(CabType.SEDAN, "NY")).thenReturn(cabs);

        List<Cab> resultCabs = cabService.viewCabByTypeAndLocation(CabType.SEDAN, "NY");

        assertFalse(resultCabs.isEmpty());
        assertEquals(2, resultCabs.size());
    }

    @Test
    public void viewCabByTypeAndLocation_NoCabs_ShouldThrowException() {
        when(cabRepo.findByCabTypeAndCurrentLocation(CabType.SEDAN, "NY")).thenReturn(Collections.emptyList());

        assertThrows(CabException.class, () -> {
            cabService.viewCabByTypeAndLocation(CabType.SEDAN, "NY");
        });
    }

    @Test
    public void viewCabByAvailability_CabsAvailable_ShouldReturnListOfCabs() throws Throwable {
        List<Cab> cabs = Arrays.asList(new Cab(), new Cab());
        when(cabRepo.findCabByAvailability("Available")).thenReturn(cabs);

        List<Cab> resultCabs = cabService.viewCabByAvailability("Available");

        assertFalse(resultCabs.isEmpty());
        assertEquals(2, resultCabs.size());
    }

    @Test
    public void viewCabByAvailability_NoCabsAvailable_ShouldThrowException() {
        when(cabRepo.findCabByAvailability("Available")).thenReturn(Collections.emptyList());

        assertThrows(CabException.class, () -> {
            cabService.viewCabByAvailability("Available");
        });
    }
}
