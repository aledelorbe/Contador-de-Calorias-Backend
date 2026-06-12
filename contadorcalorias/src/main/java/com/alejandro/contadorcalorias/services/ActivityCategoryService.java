package com.alejandro.contadorcalorias.services;


import java.util.List;

import com.alejandro.contadorcalorias.entities.ActivityCategory;


public interface ActivityCategoryService {

    // Declaration of methods to use in 'serviceImp' file

    List<ActivityCategory> getCategoriesDb();

}

    