package com.alejandro.contadorcalorias.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.alejandro.contadorcalorias.Data;
import com.alejandro.contadorcalorias.TestConfig;
import com.alejandro.contadorcalorias.services.ActivityService;

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

    @Test
    void testGetActivities () throws Exception {

        // Given
        when(service.findAll()).thenReturn(Data.createActivities001());

        // Then
        mockMvc.perform(get("/api/activities"))

        .andExpect(status().isOk());

    } 

}
