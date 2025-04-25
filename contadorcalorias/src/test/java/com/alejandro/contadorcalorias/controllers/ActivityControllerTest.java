package com.alejandro.contadorcalorias.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.alejandro.contadorcalorias.TestConfig;
import com.alejandro.contadorcalorias.data.CustomCondition;
import com.alejandro.contadorcalorias.data.Data;
import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.services.ActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;

// creo queda en duda si los datos simulados se traen de la clase data o de un archivo insert.sql
@WebMvcTest(ActivityController.class)
@Import(TestConfig.class)
class ActivityControllerTest {

    // To inject the dependency that allows for mocking HTTP requests
    @Autowired
    private MockMvc mockMvc; 

    // To inject the dependency that represents the service to mock
    @Autowired
    private ActivityService service;

    @Autowired
    private ObjectMapper objectMapper;

    // To test the enpoint getActivities
    @Test
    void testGetActivities () throws Exception {

        // Given
        when(service.findAll()).thenReturn(Data.createActivities001());

        // When
        MvcResult result = mockMvc.perform(get("/api/activities"))

        // Then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(4)))
        .andExpect(jsonPath("$[0].id").value("0000001"))
        .andExpect(jsonPath("$[0].category").value("ejercicio"))
        .andExpect(jsonPath("$[0].name").value("curl martillo"))
        .andExpect(jsonPath("$[0].calories").value(500))
        .andReturn()
        ;

        // Convert the response to a list of objects
        String jsonString = result.getResponse().getContentAsString();
        List<Activity> activities = Arrays.asList(objectMapper.readValue(jsonString, Activity[].class));

        assertFalse(activities.isEmpty());
        assertEquals(4, activities.size());
        assertEquals("0000001", activities.get(0).getId());
        assertEquals("ejercicio", activities.get(0).getCategory());
        assertEquals("curl martillo", activities.get(0).getName());
        assertEquals(500, activities.get(0).getCalories());

        verify(service).findAll();
    } 

    // To test the enpoint GetfindById with an existing id
    @Test
    void testGetfindByIdExistingId() throws Exception {

        // Given
        when(service.findById(anyString())).thenReturn(Optional.of(Data.createActivity004()));

        // When
        MvcResult result = mockMvc.perform(get("/api/activity/0000001"))

        // Then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.id").value("0000004"))
        .andExpect(jsonPath("$.category").value("comida"))
        .andExpect(jsonPath("$.name").value("Pambazos"))
        .andExpect(jsonPath("$.calories").value(800))
        .andReturn()
        ;

        // Convert the response to an object
        String jsonString = result.getResponse().getContentAsString();
        Activity activity = objectMapper.readValue(jsonString, Activity.class);

        assertNotNull(activity);
        assertEquals("0000004", activity.getId());
        assertEquals("comida", activity.getCategory());
        assertEquals("Pambazos", activity.getName());
        assertEquals(800, activity.getCalories());

        verify(service).findById(argThat(new CustomCondition(Data.idsValid, true)));
    }
    
    // To test the enpoint GetfindById with an inexisting id
    @Test
    void testGetfindByIdInexistingId() throws Exception {
        
        // Given
        when(service.findById(anyString())).thenReturn(Optional.empty());
        
        // When
        mockMvc.perform(get("/api/activity/0000008"))
        
        // Then
        .andExpect(status().isNotFound())
        .andExpect(content().string(""))
        ;

        verify(service).findById(argThat(new CustomCondition(Data.idsValid, false)));
    }

    // To test the enpoint post
    @Test
    void testPostSave() throws Exception {

        // Given
        Activity activityInsert = new Activity(null, "comida", "tacos", 620);
        when(service.save(any(Activity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        // when
        MvcResult result = mockMvc.perform(post("/api/activity")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(activityInsert)))

        
        // then
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.category").value("comida"))
            .andExpect(jsonPath("$.name").value("tacos"))
            .andExpect(jsonPath("$.calories").value(620))
            .andReturn()
            ;

        // Convert the response to an object
        String jsonString = result.getResponse().getContentAsString();
        Activity newActivity = objectMapper.readValue(jsonString, Activity.class);

        assertEquals("comida", newActivity.getCategory());
        assertEquals("tacos", newActivity.getName());
        assertEquals(620, newActivity.getCalories());

        verify(service).save(any(Activity.class));
    }

    // To test the enpoint update when we use an existing id 
    @Test
    void testPutUpdateExistingId() throws Exception {
    
        // Given
        String idToUpdate = "0000002";
        Activity activityToUpdate = new Activity(null, "ejercicio", "activity update", 400);
        when(service.update(anyString(), any(Activity.class))).thenAnswer(invocation -> Optional.of(invocation.getArgument(1)));

        // When
        MvcResult result = mockMvc.perform(put("/api/activity/" + idToUpdate)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(activityToUpdate)))

        // then
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.category").value("ejercicio"))
            .andExpect(jsonPath("$.name").value("activity update"))
            .andExpect(jsonPath("$.calories").value(400))
            .andReturn()
            ;

        // Convert the response to an object
        String jsonString = result.getResponse().getContentAsString();
        Activity newActivity = objectMapper.readValue(jsonString, Activity.class);

        assertEquals("ejercicio", newActivity.getCategory());
        assertEquals("activity update", newActivity.getName());
        assertEquals(400, newActivity.getCalories());

        verify(service).update(argThat(new CustomCondition(Data.idsValid, true)), any(Activity.class));
    }

    // To test the enpoint update when we use an inexisting id 
    @Test
    void testPutUpdateInexistingId() throws Exception {
    
        // Given
        String idToUpdate = "0000008";
        Activity activityToUpdate = new Activity(null, "ejercicio", "activity update", 400);
        when(service.update(anyString(), any(Activity.class))).thenReturn(Optional.empty());

        // When
        mockMvc.perform(put("/api/activity/" + idToUpdate)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(activityToUpdate)))

        // then
            .andExpect(status().isNotFound())
            .andExpect(content().string(""))
            ;

        verify(service).update(argThat(new CustomCondition(Data.idsValid, false)), any(Activity.class));
    }

    // To test the enpoint delete when we use an existing id 
    @Test
    void testDeleteExistingId() throws Exception {
    
        // Given
        String idToDelete = "0000001";
        when(service.deleteById(anyString())).thenReturn(Optional.of(Data.createActivity001()));

        // When
        MvcResult result = mockMvc.perform(delete("/api/activity/" + idToDelete))

        // then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("0000001"))
            .andExpect(jsonPath("$.category").value("ejercicio"))
            .andExpect(jsonPath("$.name").value("curl martillo"))
            .andExpect(jsonPath("$.calories").value(500))
            .andReturn()
            ;

        // Convert the response to an object
        String jsonString = result.getResponse().getContentAsString();
        Activity newActivity = objectMapper.readValue(jsonString, Activity.class);

        assertEquals("0000001", newActivity.getId());
        assertEquals("ejercicio", newActivity.getCategory());
        assertEquals("curl martillo", newActivity.getName());
        assertEquals(500, newActivity.getCalories());

        verify(service).deleteById(argThat(new CustomCondition(Data.idsValid, true)));
    }

    // To test the enpoint delete when we use an inexisting id 
    @Test
    void testDeleteInexistingId() throws Exception {
    
        // Given
        String idToDelete = "0000009";
        when(service.deleteById(anyString())).thenReturn(Optional.empty());

        // When
        mockMvc.perform(delete("/api/activity/" + idToDelete))

        // then
            .andExpect(status().isNotFound())
            .andExpect(content().string(""))
            ;

        verify(service).deleteById(argThat(new CustomCondition(Data.idsValid, false)));
    }

    // To test the enpoint deleteAll
    @Test
    void testDeleteAll() throws Exception {
    
        // When
        mockMvc.perform(delete("/api/activities"))

        // then
            .andExpect(status().isOk())
            .andExpect(content().string(""))
            ;

        verify(service).deleteAll();
    }
}
