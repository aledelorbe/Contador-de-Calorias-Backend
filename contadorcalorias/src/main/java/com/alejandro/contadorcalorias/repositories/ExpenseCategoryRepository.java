package com.alejandro.contadorcalorias.repositories;


import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


// The interface called 'MongoRepository' allows the use of crud operations
@Repository
public interface ExpenseCategoryRepository extends MongoRepository<ExpenseCategory, String> {

} 
