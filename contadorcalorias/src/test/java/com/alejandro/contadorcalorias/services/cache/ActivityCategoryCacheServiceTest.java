package com.alejandro.contadorcalorias.services.cache;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejandro.contadorcalorias.data.ActivityCategoryData;
import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.repositories.RedisCacheRepository;
import com.alejandro.contadorcalorias.services.ActivityCategoryService;

import com.fasterxml.jackson.core.type.TypeReference;


@ExtendWith(MockitoExtension.class)
class ActivityCategoryCacheServiceTest {
    
    // To create the mocks
    @Mock
    private ActivityCategoryService activityCategoryService;

    @Mock
    private RedisCacheRepository redisCacheRepository;

    // To create a service object with the injection of a mock
    @InjectMocks
    private ActivityCategoryCacheService activityCategoryCacheService;

    String keyActivityCategories = "activityCategories";


    // To test the getCategories method when the data return from cache with redis
    @Test
    void shouldReturnCategoriesFromCache() {

        // Given
        List<ActivityCategory> cachedCategories = ActivityCategoryData.createActivityCategories001();

        when(redisCacheRepository.get(
                eq(keyActivityCategories),
                any(TypeReference.class)))
                .thenReturn(cachedCategories);

        // When
        List<ActivityCategory> result = activityCategoryCacheService.getCategories();

        // Then
        assertEquals(cachedCategories, result);

        verify(redisCacheRepository).get(
                eq(keyActivityCategories),
                any(TypeReference.class));

        verifyNoInteractions(activityCategoryService);

        verify(redisCacheRepository, never())
                .set(anyString(), any(), anyLong());
    }

    // To test the getCategories method when the data return from db
    @Test
    void shouldReturnCategoriesFromDatabaseAndSaveInCache() {

        // Given
        List<ActivityCategory> dbCategories = ActivityCategoryData.createActivityCategories001();

        when(redisCacheRepository.get(
                eq(keyActivityCategories),
                any(TypeReference.class)))
                .thenReturn(null);

        when(activityCategoryService.getCategoriesDb())
                .thenReturn(dbCategories);

        // When
        List<ActivityCategory> result = activityCategoryCacheService.getCategories();

        // Then
        assertEquals(dbCategories, result);

        verify(redisCacheRepository).get(
                eq(keyActivityCategories),
                any(TypeReference.class));

        verify(activityCategoryService)
                .getCategoriesDb();

        verify(redisCacheRepository)
                .set(keyActivityCategories, dbCategories, 15L);
    }

}

