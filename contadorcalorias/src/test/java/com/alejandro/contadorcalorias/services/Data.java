package com.alejandro.contadorcalorias.services;

import java.util.Arrays;
import java.util.List;

import com.alejandro.contadorcalorias.entities.Activity;

// The class that contains the data to be mocked
class Data {

    static Activity createActivity001() {
        return new Activity("0000001", "ejercicio", "curl martillo", 500);
    }

    static Activity createActivity002() {
        return new Activity("0000002", "ejercicio", "saltar cuerda", 340);
    }

    static Activity createActivity003() {
        return new Activity("0000003", "comida", "Manzana", 230);
    }

    static Activity createActivity004() {
        return new Activity("0000004", "comida", "Pambazos", 800);
    }

    static List<Activity> createActivities001() {
        return Arrays.asList(createActivity001(), createActivity002(), createActivity003(), createActivity004());
    }
}
