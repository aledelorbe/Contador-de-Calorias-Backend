package com.alejandro.contadorcalorias;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.alejandro.contadorcalorias.services.ActivityService;

// We use this class to create components in the test context
@TestConfiguration
public class TestConfig {

    // Create the component that represents the service to mock
    @Bean
    public ActivityService activityService() {
        return Mockito.mock(ActivityService.class);
    } 
}
