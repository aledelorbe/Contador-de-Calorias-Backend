package com.alejandro.contadorcalorias.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.contadorcalorias.entities.Activity;

public interface ActivityService {
    
    // Declaration of methods to use in 'serviceImp' file
    
    List<Activity> findAll();
    
    Optional<Activity> findById(String id);
    
    Activity save(Activity activity);

    Optional<Activity> update(String id, Activity activity);

    Optional<Activity> deleteById(String id);

    void deleteAll();
}
