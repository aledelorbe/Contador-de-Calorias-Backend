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

    // To get a specific activity based on its id
    @Override
    @Transactional(readOnly = true)
    public Optional<Activity> findById(String id) {
        return repository.findById(id);
    }

    // To save a new activity in the db
    @Override
    @Transactional
    public Activity save(Activity activity){
        return repository.save(activity);
    }

    // To update a specific activity based on its id
    @Override
    @Transactional
    public Optional<Activity> update(String id, Activity activity) {
        // Search for a specific activity 
        Optional<Activity> optionalActivity = repository.findById(id);
        
        // If the activity is present then...
        if( optionalActivity.isPresent() ) {
            // update that record and return an optional value
            Activity activityDb = optionalActivity.get();

            activityDb.setName(activity.getName());
            activityDb.setCalories(activity.getCalories());
            activityDb.setCategory(activity.getCategory());

            return Optional.ofNullable(repository.save(activityDb));
        }

        return optionalActivity;
    }

    // To delete a specific activity based on its id
    @Override
    @Transactional
    public Optional<Activity> deleteById(String id) {
        // Search a specific activity 
        Optional<Activity> optionalActivity = repository.findById(id);
        
        // If the activity is present then delete that activity
        optionalActivity.ifPresent(activityDb -> {
            repository.deleteById(id);
        });

        return optionalActivity;
    }

    // To delete all of activities
    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}
