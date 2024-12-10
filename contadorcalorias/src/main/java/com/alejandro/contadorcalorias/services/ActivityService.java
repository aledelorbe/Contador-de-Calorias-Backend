package com.alejandro.contadorcalorias.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.contadorcalorias.entities.Activity;

public interface ActivityService {
    
    // Declaration of methods to use in 'serviceImp' file
    
    public List<Activity> findAll();
    
    public Optional<Activity> findById(String id);
    
    public Activity save(Activity activity);

    public Optional<Activity> update(String id, Activity activity);

    public Optional<Activity> delete(String id);
}
