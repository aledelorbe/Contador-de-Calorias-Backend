package com.alejandro.contadorcalorias.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.alejandro.contadorcalorias.entities.Activity;

@Service
public interface ActivityService {
    
    public void save(Activity activity);

    public List<Activity> findAll();

}
