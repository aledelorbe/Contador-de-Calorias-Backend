package com.alejandro.contadorcalorias.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.services.ExpenseCategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController // To create an api rest.
@RequestMapping("/api/expense-categories") // To create a base path.
public class ExpenseCategoryController {
    
    // To Inject the service dependency
    @Autowired
    private ExpenseCategoryService ExpenseCategoryService;


    // To create an endpoint that allows invocating the method findAll.
    @GetMapping()
    public List<ExpenseCategory> expenseCategories() {
        return ExpenseCategoryService.findAll();
    }

}