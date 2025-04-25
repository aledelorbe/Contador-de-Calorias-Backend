package com.alejandro.contadorcalorias.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejandro.contadorcalorias.data.CustomCondition;
import com.alejandro.contadorcalorias.data.Data;
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

        // Given
        when(repository.findAll()).thenReturn(Data.createActivities001());

        // when
        List<Activity> activities = service.findAll();

        // then
        assertNotNull(activities);
        assertEquals(4, activities.size());
        assertEquals("0000002", activities.get(1).getId());
        assertEquals("ejercicio", activities.get(1).getCategory());
        assertEquals("saltar cuerda", activities.get(1).getName());
        assertEquals(340, activities.get(1).getCalories());

        verify(repository).findAll();
    }

    // To test the metod findById when we use an existing id
    @Test
    void findByIdExistingIdTest() {

        // Given
        when(repository.findById(anyString())).thenReturn(Optional.of(Data.createActivity004()));

        // when
        Optional<Activity> optionalActivity = service.findById("0000004");

        // then
        assertNotNull(optionalActivity.get());
        assertEquals("0000004", optionalActivity.get().getId());
        assertEquals("comida", optionalActivity.get().getCategory());
        assertEquals("Pambazos", optionalActivity.get().getName());
        assertEquals(800, optionalActivity.get().getCalories());
        
        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, true)));
    }

    // To test the metod findById when we use an inexisting id
    @Test
    void findByIdInexistingIdTest() {

        // Given
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // when
        Optional<Activity> optionalActivity2 = service.findById("0000006");

        // then
        assertFalse(optionalActivity2.isPresent());
        assertThrows(NoSuchElementException.class, () -> {
            optionalActivity2.orElseThrow();
        });

        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, false)));
    }
    
    // To test the metod save
    @Test
    void saveTest() {

        // Given
        Activity activityInsert = new Activity(null, "comida", "tacos", 620);
        when(repository.save(any(Activity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // when
        Activity newActivity = service.save(activityInsert);
        
        // then
        assertEquals("comida", newActivity.getCategory());
        assertEquals("tacos", newActivity.getName());
        assertEquals(620, newActivity.getCalories());

        verify(repository).save(any(Activity.class));
    }

    // To test the metod update when we use an existing id
    @Test
    void updateExistingIdTest() {

        // Given
        String idToUpdate = "0000001";
        Activity activityToUpdate = new Activity(null, "ejercicio", "activity update", 400);
        when(repository.findById(anyString())).thenReturn(Optional.of(Data.createActivity001()));
        when(repository.save(any(Activity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Optional<Activity> result = service.update(idToUpdate, activityToUpdate);

        // Then
        assertTrue(result.isPresent());
        assertEquals("activity update", result.get().getName());
        assertEquals(400, result.get().getCalories());
        assertEquals("ejercicio", result.get().getCategory());

        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, true)));
        verify(repository).save(any(Activity.class));
    }

    // To test the metod update when we use an inexisting id
    @Test
    void updateInexistingIdTest() {

        String idToUpdate = "0000005";
        Activity activityToUpdate = new Activity(null, "ejercicio", "activity update", 400);
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // When
        Optional<Activity> result2 = service.update(idToUpdate, activityToUpdate);

        // Then
        assertFalse(result2.isPresent());
        assertThrows(NoSuchElementException.class, () -> {
            result2.orElseThrow();
        });

        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, false)));
        verify(repository, never()).save(any(Activity.class));
    }

    // To test the metod delete when we use an existing id
    @Test
    void deleteExistingIdTest() {

        // Given
        String idToDelete = "0000001";
        when(repository.findById(anyString())).thenReturn(Optional.of(Data.createActivity001()));

        // When
        Optional<Activity> result = service.deleteById(idToDelete);

        // Then
        assertTrue(result.isPresent());
        assertEquals("curl martillo", result.get().getName());
        assertEquals(500, result.get().getCalories());
        assertEquals("ejercicio", result.get().getCategory());

        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, true)));
        verify(repository).deleteById(argThat(new CustomCondition(Data.idsValid, true)));
    }

    // To test the metod delete when we use an inexisting id
    @Test
    void deleteInexistingIdTest() {

        // Given
        String idToDelete = "0000009";
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // When
        Optional<Activity> result = service.deleteById(idToDelete);

        // Then
        assertFalse(result.isPresent());
        assertThrows(NoSuchElementException.class, () -> {
            result.orElseThrow();
        });

        verify(repository).findById(argThat(new CustomCondition(Data.idsValid, false)));
        verify(repository, never()).deleteById(argThat(new CustomCondition(Data.idsValid, false)));
    }

    // To test the metod deleteAll
    @Test
    void deleteAllTest() {

        // when
        service.deleteAll();

        // then
        verify(repository).deleteAll();
    }

}
