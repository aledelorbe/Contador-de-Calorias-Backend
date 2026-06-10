package com.alejandro.contadorcalorias.controllers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.alejandro.contadorcalorias.data.ExpenseCategoryData;
import com.alejandro.contadorcalorias.entities.ExpenseCategory;
import com.alejandro.contadorcalorias.services.cache.ExpenseCategoryCacheService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(ExpenseCategoryController.class)
// @Import(TestConfig.class)
class ExpenseCategoryControllerTest {

    // To inject the dependency that allows for mocking HTTP requests
    @Autowired
    private MockMvc mockMvc; 

    // To inject the dependency that represents the service to mock
    // @Autowired
    @MockitoBean
    private ExpenseCategoryCacheService expenseCategoryCacheService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/expense-categories";


    // To test the endpoint getExpenseCategories
    @Test
    void getExpenseCategoriesTest () throws Exception {

        // Given
        when(expenseCategoryCacheService.getCategories()).thenReturn(ExpenseCategoryData.createExpenseCategories001());

        // When
        MvcResult result = mockMvc.perform(get(BASE_URL))

        // Then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2)))

            .andExpect(jsonPath("$[0].categoryName").value("food"))
            .andExpect(jsonPath("$[1].categoryName").value("exercise"))
            .andReturn()
        ;

        // Convert the response to a list of objects
        String jsonString = result.getResponse().getContentAsString();
        List<ExpenseCategory> expenseCategories = Arrays.asList(objectMapper.readValue(jsonString, ExpenseCategory[].class));

        assertFalse(expenseCategories.isEmpty());
        assertEquals(2, expenseCategories.size());

        assertEquals("food", expenseCategories.get(0).getCategoryName());
        assertEquals("exercise", expenseCategories.get(1).getCategoryName());

        verify(expenseCategoryCacheService).getCategories();
    } 

}