package com.alejandro.contadorcalorias.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

// To specific the name of collection in mongoDb
// In mongoDb the name of this collection is 'activities' but in this project 
// the name of this class is 'Activity'
@Document(collection = "activities")
public class Activity {

    // Mapping of class attributes with collection fields in mongoDb
    @Id
    private String id;

    private String category;

    private String name;

    private int calories;

    public Activity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
