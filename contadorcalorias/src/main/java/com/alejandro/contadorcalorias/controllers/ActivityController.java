package com.alejandro.contadorcalorias.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.services.ActivityService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    // To create an endpoint that allows invocating the method fingById.
    @GetMapping("/activity/{id}")
    public ResponseEntity<?> activity(@PathVariable String id) {
        // Search a specific activity and if it's present then return it.
        Optional<Activity> optionalActivity = service.findById(id);
        if( optionalActivity.isPresent() ) {
            return ResponseEntity.ok(optionalActivity.orElseThrow());
        }
        // Else returns code response 404 
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows invocating the method save.
    // The annotation called 'RequestBody' allows receiving data of a client
    @PostMapping("/activity")
    public ResponseEntity<?> saveActivity(@Valid @RequestBody Activity activity, BindingResult result) {
        // To handle the obligations of object attributes
        if( result.hasFieldErrors() ){
            return validation(result);
        } 

        // When a new activity is created to respond return the same activity
        Activity newActivity = service.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newActivity);
    }

    // To create an endpoint that allows update all of atributte values a specific activity based its id.
    @PutMapping("/activity/{id}")
    public ResponseEntity<?> updateActivity(@Valid @RequestBody Activity activity, BindingResult result, @PathVariable String id) {
        // To handle of obligations of object attributes
        if( result.hasFieldErrors() ){
            return validation(result);
        } 
        
        // Find specific activity and if it's present then return specific activity
        Optional<Activity> optionalActivity = service.update(id, activity);

        if( optionalActivity.isPresent() ) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalActivity.orElseThrow());
        }
        // Else return code response 404 
        return ResponseEntity.notFound().build();
    }

    // To create an endpoint that allows deleting a specific activity based its id.
    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable String id) {
        // Find specific activity and if it's present then return specific activity
        Optional<Activity> optionalActivity = service.deleteById(id);
        if( optionalActivity.isPresent() ) {
            return ResponseEntity.ok(optionalActivity.orElseThrow());
        }
        // Else return code response 404 
        return ResponseEntity.notFound().build();
    }

    // To create a endpoint that allows deleting all of activities
    // and return response ok
    @DeleteMapping("/activities")
    public ResponseEntity<?> deleteAllOfActivity() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    // To send a JSON object with messages about the obligations of each object attribute
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
