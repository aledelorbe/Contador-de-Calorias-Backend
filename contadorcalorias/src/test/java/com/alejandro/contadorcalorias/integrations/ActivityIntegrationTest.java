package com.alejandro.contadorcalorias.integrations;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.repositories.ActivityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


public class ActivityIntegrationTest extends AbstractMongoIntegrationTest {

    // To inject the component of testRestTemplate
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/activities";

    private static final String PUT_DELETE_URL = BASE_URL + "/";

    // To load data
    @BeforeEach
    void setUp() throws Exception {

        activityRepository.deleteAll();

        File file =
            ResourceUtils.getFile(
                "classpath:activities.json"
            );

        List<Activity> activities =
            objectMapper.readValue(
                file,
                new TypeReference<List<Activity>>() {}
            );

        System.out.println(activities);
        activityRepository.saveAll(activities);
    }

    // To test the activities endpoint
    @Test
    void activitiesIntegrationTest() {

        // When
        ResponseEntity<Activity[]> response  = testRestTemplate.getForEntity(BASE_URL, Activity[].class);
        List<Activity> activities = Arrays.asList(response.getBody()); 

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(activities.isEmpty());
        assertEquals(4, activities.size());
        assertEquals("1000000", activities.get(0).getId());
        assertEquals("Chicken Breast", activities.get(0).getName());
        assertEquals("food", activities.get(0).getCategory());
        assertEquals(165, activities.get(0).getCalories());

    }

    // To test the save endpoint
    @Test
    void postActivityIntegrationTest() {

        // Given
        Activity activityInsert = new Activity(null, "Food", "Cheescake", 600);

        // When
        ResponseEntity<Activity> response = testRestTemplate.postForEntity(BASE_URL, activityInsert, Activity.class);
        Activity newActivity = response.getBody();

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cheescake", newActivity.getName());
        assertEquals("Food", newActivity.getCategory());
        assertEquals(600, newActivity.getCalories());

    }

    // To test the update endpoint when we use an existing id 
    @Test
    void putUpdateExistingIdIntegrationTest()  {

        // Given
        String idToUpdate = "2000000";
        Activity activityToUpdate = new Activity(null, "Exercise", " Jump ", 250);
        
        // When
        HttpEntity<Activity> request = new HttpEntity<>(activityToUpdate);
        ResponseEntity<Activity> response = testRestTemplate.exchange(PUT_DELETE_URL + idToUpdate, HttpMethod.PUT, request, Activity.class);

        // Then
        Activity activityUpdate = response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("2000000", activityUpdate.getId());
        assertEquals("Jump", activityUpdate.getName());
        assertEquals("Exercise", activityUpdate.getCategory());
        assertEquals(250, activityUpdate.getCalories());

    }

    // To test the update endpoint when we use an unexisting id
    @Test
    void putUpdateUnexistingIdIntegrationTest()  {

        // Given
        String idToUpdate = "999999";
        Activity activityToUpdate = new Activity(null, "Exercise", " Jump ", 250);
        HttpEntity<Activity> request = new HttpEntity<>(activityToUpdate);
        
        // When
        ResponseEntity<?> response = testRestTemplate.exchange(PUT_DELETE_URL + idToUpdate, HttpMethod.PUT, request, Void.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

    }

    // To test the delete endpoint when we use an existing id 
    @Test
    void deleteExistingIdIntegrationTest() {

        // Given
        String idToDelete = "3000000";
        
        // When
        ResponseEntity<Activity> response = testRestTemplate.exchange(PUT_DELETE_URL + idToDelete, HttpMethod.DELETE, null, Activity.class);

        // Then
        Activity activityDelete = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("3000000", activityDelete.getId());
        assertEquals("Running", activityDelete.getName());
        assertEquals("exercise", activityDelete.getCategory());
        assertEquals(300, activityDelete.getCalories());

        // When
        ResponseEntity<Activity[]> response2  = testRestTemplate.getForEntity(BASE_URL, Activity[].class);
        List<Activity> activities = Arrays.asList(response2.getBody()); 

        // Then
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertFalse(activities.isEmpty());
        assertEquals(3, activities.size());
        
    }

    // To test the delete endpoint when we use an unexisting id
    @Test
    void deleteUnexistingIdIntegrationTest() {
    
        // Given
        String idToDelete = "999999";
        
        // When
        ResponseEntity<?> response = testRestTemplate.exchange(PUT_DELETE_URL + idToDelete, HttpMethod.DELETE, null, Void.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

    }

    // To test the deleteAll endpoint
    @Test
    void deleteAllIntegrationTest() {

        // When
        ResponseEntity<?> response = testRestTemplate.exchange(BASE_URL, HttpMethod.DELETE, null, Void.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        // When
        ResponseEntity<Activity[]> response2  = testRestTemplate.getForEntity(BASE_URL, Activity[].class);
        List<Activity> activities = Arrays.asList(response2.getBody()); 

        // Then
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertTrue(activities.isEmpty());
        assertEquals(0, activities.size());

    }
    
}

