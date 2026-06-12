package com.alejandro.contadorcalorias.data;


import java.util.Arrays;
import java.util.List;

import com.alejandro.contadorcalorias.entities.ActivityCategory;


// The class that contains the data to be mocked in the service methods
public class ActivityCategoryData {

    public static final List<String> idsValid = Arrays.asList("0000001", "0000002");


    public static ActivityCategory createActivityCategory001() {
        return new ActivityCategory(idsValid.get(0), "food");
    }

    public static ActivityCategory createActivityCategory002() {
        return new ActivityCategory(idsValid.get(1), "exercise");
    }

    public static List<ActivityCategory> createActivityCategories001() {
        return Arrays.asList(createActivityCategory001(), createActivityCategory002());
    }
    
}
