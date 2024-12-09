package com.alejandro.contadorcalorias.services;

import java.util.List;
import java.util.Optional;

import com.alejandro.contadorcalorias.entities.Activity;

public interface ActivityService {
    
    // Declaration of methods to use in 'serviceImp' file
    
    public List<Activity> findAll();
    
    public void save(Activity activity);

    public Optional<Activity> findById(String id);

    public void deleteById(String id);
}
