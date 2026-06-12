package com.alejandro.contadorcalorias.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.repositories.ActivityCategoryRepository;


@Service
public class ActivityCategoryServiceImp implements ActivityCategoryService {
    
    // To inject the repository dependency.
    @Autowired
    private ActivityCategoryRepository repository;


    // To list all activities (records) in the collection 'activities'
    @Override
    @Transactional(readOnly = true)
    public List<ActivityCategory> getCategoriesDb(){
        return repository.findAll();
    }

}
