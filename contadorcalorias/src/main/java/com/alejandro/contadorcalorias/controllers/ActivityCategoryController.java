package com.alejandro.contadorcalorias.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.services.cache.ActivityCategoryCacheService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController // To create an api rest.
@RequestMapping("/api/activity-categories") // To create a base path.
public class ActivityCategoryController {
    
    // To Inject the service dependency
    @Autowired
    private ActivityCategoryCacheService activityCategoryCacheService;

    private static final Logger logger = LoggerFactory.getLogger(ActivityCategoryController.class);


    // To create an endpoint that allows invocating the getCategories method.
    @GetMapping
    public List<ActivityCategory> activityCategories() {

        long startTime = System.nanoTime();

        List<ActivityCategory> categories = activityCategoryCacheService.getCategories();

        long durationMs = (System.nanoTime() - startTime) / 1_000_000;

        logger.debug("The execution time is: {} ms", durationMs);

        return categories;
    }

}