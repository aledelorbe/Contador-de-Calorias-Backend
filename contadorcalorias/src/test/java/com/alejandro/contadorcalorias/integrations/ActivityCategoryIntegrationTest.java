package com.alejandro.contadorcalorias.integrations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.repositories.ActivityCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


class ActivityCategoryIntegrationTest extends AbstractMongoIntegrationTest {

    // To inject the component of testRestTemplate
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ActivityCategoryRepository activityCategoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/activity-categories";

    @BeforeEach
    void setUp() throws Exception {

        activityCategoryRepository.deleteAll();

        File file =
            ResourceUtils.getFile(
                "classpath:activityCategories.json"
            );

        List<ActivityCategory> categories =
            objectMapper.readValue(
                file,
                new TypeReference<List<ActivityCategory>>() {}
            );

        activityCategoryRepository.saveAll(categories);
    }

    // To test the activityCategories endpoint
    @Test
    void activityCategoriesIntegrationTest() {

        // When
        ResponseEntity<ActivityCategory[]> response  = testRestTemplate.getForEntity(BASE_URL, ActivityCategory[].class);
        List<ActivityCategory> ActivityCategories = Arrays.asList(response.getBody()); 

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(ActivityCategories.isEmpty());
        assertEquals(2, ActivityCategories.size());
        assertEquals("0000001", ActivityCategories.get(0).getId());
        assertEquals("food", ActivityCategories.get(0).getCategoryName());

    }
    
}
