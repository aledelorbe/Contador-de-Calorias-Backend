package com.alejandro.contadorcalorias.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejandro.contadorcalorias.data.ActivityCategoryData;
import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.repositories.ActivityCategoryRepository;


@ExtendWith(MockitoExtension.class)
class ActivityCategoryServiceImpTest {

    // To create a mock
    @Mock
    ActivityCategoryRepository activityCategoryRepository;

    // To create a service object with the injection of a mock
    @InjectMocks
    ActivityCategoryServiceImp service;


    // To test the getCategoriesDb method
    @Test
    void findAllTest() {

        // Given
        when(activityCategoryRepository.findAll()).thenReturn(ActivityCategoryData.createActivityCategories001());

        // when
        List<ActivityCategory> activityCategories = service.getCategoriesDb();

        // then
        assertNotNull(activityCategories);
        assertEquals(2, activityCategories.size());

        assertEquals("0000002", activityCategories.get(1).getId());
        assertEquals("exercise", activityCategories.get(1).getCategoryName());

        verify(activityCategoryRepository).findAll();
    }

}