package com.alejandro.contadorcalorias.services.cache;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alejandro.contadorcalorias.entities.ActivityCategory;
import com.alejandro.contadorcalorias.repositories.RedisCacheRepository;
import com.alejandro.contadorcalorias.services.ActivityCategoryService;

import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class ActivityCategoryCacheService {
    

    // To Inject the service dependency
    @Autowired
    private ActivityCategoryService activityCategoryService;

    @Autowired
    private RedisCacheRepository redisCacheRepository;


    public List<ActivityCategory> getCategories() {

        String key = "activityCategories";

        List<ActivityCategory> cached = redisCacheRepository.get(
            key,
            new TypeReference<List<ActivityCategory>>() {}
        );

        if (cached != null) {
            return cached;
        }

        List<ActivityCategory> fromDb = activityCategoryService.getCategoriesDb();

        redisCacheRepository.set(key, fromDb, 15L);

        return fromDb;
    }

}
