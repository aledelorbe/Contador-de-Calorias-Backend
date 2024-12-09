package com.alejandro.contadorcalorias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.repositories.ActivityRepository;

@Service
public class ActivityServiceImp implements ActivityService{
    
    // To inject the repository dependency.
    @Autowired
    private ActivityRepository repository;
    
    // To list all of activities (records) in the collection 'activities'
    @Override
    @Transactional(readOnly = true)
    public List<Activity> findAll(){
        return repository.findAll();
    }

    // To save a new activity in the db
    @Override
    @Transactional
    public void save(Activity activity){
        repository.save(activity);
    }

    // To get a specific activity based on its id
    @Override
    @Transactional
    public Optional<Activity> findById(String id) {
        return repository.findById(id);
    }

    // To delete a specific activity based on its id
    @Override
    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);        
    }
}
