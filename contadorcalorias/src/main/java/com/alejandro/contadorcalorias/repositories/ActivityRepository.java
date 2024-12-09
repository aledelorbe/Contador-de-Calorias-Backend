package com.alejandro.contadorcalorias.repositories;

import com.alejandro.contadorcalorias.entities.Activity;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

} 
