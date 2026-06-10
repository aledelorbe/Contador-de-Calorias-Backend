package com.alejandro.contadorcalorias.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.repositories.ExpenseCategoryRepository;


@Service
public class ExpenseCategoryServiceImp implements ExpenseCategoryService {
    
    // To inject the repository dependency.
    @Autowired
    private ExpenseCategoryRepository repository;


    // To list all activities (records) in the collection 'activities'
    @Override
    @Transactional(readOnly = true)
    public List<ExpenseCategory> getCategoriesDb(){
        return repository.findAll();
    }

}
