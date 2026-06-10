package com.alejandro.contadorcalorias.controllers;


import org.springframework.web.bind.annotation.RestController;

import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.services.cache.ExpenseCategoryCacheService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController // To create an api rest.
@RequestMapping("/api/expense-categories") // To create a base path.
public class ExpenseCategoryController {
    
    // To Inject the service dependency
    @Autowired
    private ExpenseCategoryCacheService expenseCategoryCacheService;

    private static final Logger logger = LoggerFactory.getLogger(ExpenseCategoryController.class);


    // To create an endpoint that allows invocating the getCategories method.
    @GetMapping
    public List<ExpenseCategory> expenseCategories() {

        long startTime = System.nanoTime();

        List<ExpenseCategory> categories = expenseCategoryCacheService.getCategories();

        long durationMs = (System.nanoTime() - startTime) / 1_000_000;

        logger.debug("The execution time is: {} ms", durationMs);

        return categories;
    }

}