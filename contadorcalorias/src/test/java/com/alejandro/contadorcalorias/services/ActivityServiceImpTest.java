package com.alejandro.contadorcalorias.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.repositories.ActivityRepository;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImpTest {

    // To create a mock
    @Mock
    ActivityRepository repository;

    // To create a service object with the injection of a mock
    @InjectMocks
    ActivityServiceImp service;

    // To test the metod findAll
    @Test
    void findAllTest() {

        // Step: Given
        when(repository.findAll()).thenReturn(Data.createActivities001());

        // Step: when
        List<Activity> activities = service.findAll();

        // Step: then
        assertNotNull(activities);
        assertEquals(4, activities.size());
        assertEquals("0000002", activities.get(1).getId());
        assertEquals("ejercicio", activities.get(1).getCategory());
        assertEquals("saltar cuerda", activities.get(1).getName());
        assertEquals(340, activities.get(1).getCalories());

        verify(repository).findAll();
    }

    // To test the metod findById
    @Test
    void findByIdTest() {

        Set<String> idsValid = Set.of("0000001", "0000002", "0000003", "0000004");

        // With an existing object ---------------------------------
        // Step: Given
        when(repository.findById(anyString())).thenReturn(Optional.of(Data.createActivity004()));

        // Step: when
        Optional<Activity> optionalActivity = service.findById("0000004");

        // Step: then
        assertNotNull(optionalActivity.get());
        assertEquals("0000004", optionalActivity.get().getId());
        assertEquals("comida", optionalActivity.get().getCategory());
        assertEquals("Pambazos", optionalActivity.get().getName());
        assertEquals(800, optionalActivity.get().getCalories());
        
        verify(repository).findById(argThat(new CustomCondition(idsValid, true)));

        // With an inexisting object ---------------------------------
        // Step: Given
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Step: when
        Optional<Activity> optionalActivity2 = service.findById("0000006");

        // Step: then
        assertFalse(optionalActivity2.isPresent());
        assertThrows(NoSuchElementException.class, () -> {
            optionalActivity2.orElseThrow();
        });

        verify(repository).findById(argThat(new CustomCondition(idsValid, false)));
        
        verify(repository, times(2)).findById(anyString());
    }

     

}
