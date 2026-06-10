package com.alejandro.contadorcalorias.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alejandro.contadorcalorias.data.ExpenseCategoryData;
import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.repositories.ExpenseCategoryRepository;


@ExtendWith(MockitoExtension.class)
class ExpenseCategoryServiceImpTest {

    // To create a mock
    @Mock
    ExpenseCategoryRepository expenseCategoryRepository;

    // To create a service object with the injection of a mock
    @InjectMocks
    ExpenseCategoryServiceImp service;


    // To test the getCategoriesDb method
    @Test
    void findAllTest() {

        // Given
        when(expenseCategoryRepository.findAll()).thenReturn(ExpenseCategoryData.createExpenseCategories001());

        // when
        List<ExpenseCategory> expenseCategories = service.getCategoriesDb();

        // then
        assertNotNull(expenseCategories);
        assertEquals(2, expenseCategories.size());

        assertEquals("0000002", expenseCategories.get(1).getId());
        assertEquals("exercise", expenseCategories.get(1).getCategoryName());

        verify(expenseCategoryRepository).findAll();
    }

}