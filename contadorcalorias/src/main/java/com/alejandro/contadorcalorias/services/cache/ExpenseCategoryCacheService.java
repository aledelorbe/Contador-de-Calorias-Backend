package com.alejandro.contadorcalorias.services.cache;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.repositories.RedisCacheRepository;
import com.alejandro.contadorcalorias.services.ExpenseCategoryService;

import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class ExpenseCategoryCacheService {
    

    // To Inject the service dependency
    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @Autowired
    private RedisCacheRepository redisCacheRepository;


    public List<ExpenseCategory> getCategories() {

        String key = "expenseCategories";

        List<ExpenseCategory> cached = redisCacheRepository.get(
            key,
            new TypeReference<List<ExpenseCategory>>() {}
        );

        if (cached != null) {
            return cached;
        }

        List<ExpenseCategory> fromDb = expenseCategoryService.getCategoriesDb();

        redisCacheRepository.set(key, fromDb, 15L);

        return fromDb;
    }


}
