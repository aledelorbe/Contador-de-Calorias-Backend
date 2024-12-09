package com.alejandro.contadorcalorias.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.contadorcalorias.entities.Activity;
import com.alejandro.contadorcalorias.repositories.ActivityRepository;

public class ActivityServiceImp implements ActivityService{
    
    @Autowired
    private ActivityRepository repository;

    @Override
    @Transactional
    public void save(Activity activity){
        repository.save(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> findAll(){
        return repository.findAll();
    }



}
