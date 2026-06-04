package com.alejandro.contadorcalorias.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;


// In mongoDb the name of this collection is 'expese_categories' but in this project
// the name of this class is 'ExpenseCategory'
@Document(collection = "expese_categories")
public class ExpenseCategory {

    // Mapping of class attributes with collection fields in mongoDb
    @Id
    private String id;

    @Field(value = "category_name")
    private String categoryName;

}