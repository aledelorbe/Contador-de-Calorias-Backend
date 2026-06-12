package com.alejandro.contadorcalorias.repositories;


import com.alejandro.contadorcalorias.entities.ActivityCategory;

import org.springframework.data.mongodb.repository.MongoRepository;


// The interface called 'MongoRepository' allows the use of crud operations
public interface ActivityCategoryRepository extends MongoRepository<ActivityCategory, String> {

} 
