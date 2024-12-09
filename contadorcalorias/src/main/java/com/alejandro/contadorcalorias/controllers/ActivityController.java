package com.alejandro.contadorcalorias.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.services.ActivityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;

    @GetMapping()
    public List<Activity> activities() {
        return activityService.findAll();
    }

    @PostMapping()
    public void saveActivity(@RequestBody Activity activity) {
       activityService.save(activity);
    }
}
