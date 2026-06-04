package com.alejandro.contadorcalorias.services;


import java.util.List;

import com.alejandro.contadorcalorias.entities.ExpenseCategory;


public interface ExpenseCategoryService {

    // Declaration of methods to use in 'serviceImp' file

    List<ExpenseCategory> findAll();

}

    