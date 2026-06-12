package com.alejandro.contadorcalorias.entities;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;


// In mongoDb the name of this collection is 'activity_categories' but in this project
// the name of this class is 'ActivityCategory'
@Document(collection = "activity_categories")
public class ActivityCategory {

    // Mapping of class attributes with collection fields in mongoDb
    @Id
    private String id;

    @Field(value = "category_name")
    private String categoryName;

    public ActivityCategory() {
    }

    public ActivityCategory(String id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}