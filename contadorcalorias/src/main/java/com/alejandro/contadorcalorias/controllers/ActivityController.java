package com.alejandro.contadorcalorias.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.services.ActivityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController // To create a api rest.
@RequestMapping("/api") // To create a base path.
public class ActivityController {
    
    // To Inject the service dependency
    @Autowired
    private ActivityService service;

    // To create an endpoint that allows invocating the method findAll.
    @GetMapping("/activities")
    public List<Activity> activities() {
        return service.findAll();
    }

    // To create an endpoint that allows invocating the method findAll.
    @GetMapping("/activity/{id}")
    public ResponseEntity<?> activity(@PathVariable String id) {
        // Find specific activity and if it's present then return specific activity
        Optional<Activity> optionalActivity = service.findById(id);
        if( optionalActivity.isPresent() ) {
            return ResponseEntity.ok(optionalActivity.orElseThrow());
        }
        // Else return code response 404 
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invocating the method save.
    // The annotation called 'RequestBody' allows receiving data of a client
    @PostMapping("/activity")
    public ResponseEntity<?> saveActivity(@RequestBody Activity activity) {
        // When a new activity is created to respond return the same activity
        Activity newActivity = service.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newActivity);
    }

    @PutMapping("/activity/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        // Find specific activity and if it's present then return specific activity
        Optional<Activity> optionalActivity = service.update(id, activity);

        if( optionalActivity.isPresent() ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalActivity.orElseThrow());
        }
        // Else return code response 404 
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable String id) {
        // Find specific activity and if it's present then return specific activity
        Optional<Activity> optionalActivity = service.delete(id);
        if( optionalActivity.isPresent() ) {
            return ResponseEntity.ok(optionalActivity.orElseThrow());
        }
        // Else return code response 404 
        return ResponseEntity.notFound().build();
    }
}
