package com.alejandro.contadorcalorias.data;

import java.util.Arrays;
import java.util.List;

import com.alejandro.contadorcalorias.entities.Activity;

// The class that contains the data to be mocked in the service methods
public class ActivityData {

    public static final List<String> idsValid = Arrays.asList("0000001", "0000002", "0000003", "0000004");

    public static Activity createActivity001() {
        return new Activity(idsValid.get(0), "ejercicio", "curl martillo", 500);
    }

    public static Activity createActivity002() {
        return new Activity(idsValid.get(1), "ejercicio", "saltar cuerda", 340);
    }

    public static Activity createActivity003() {
        return new Activity(idsValid.get(2), "comida", "Manzana", 230);
    }

    public static Activity createActivity004() {
        return new Activity(idsValid.get(3), "comida", "Pambazos", 800);
    }

    public static List<Activity> createActivities001() {
        return Arrays.asList(createActivity001(), createActivity002(), createActivity003(), createActivity004());
    }
    
}
