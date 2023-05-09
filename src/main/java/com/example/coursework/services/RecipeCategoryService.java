package com.example.coursework.services;

import com.example.coursework.entity.*;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RecipeCategoryService {
    public static final Logger LOG = LoggerFactory.getLogger(RecipeCategoryService.class);

    private final RecipeCategoryRepository recipeCategoryRepository;
    @Autowired
    public RecipeCategoryService(RecipeCategoryRepository recipeCategoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
    }

    public List<RecipeCategory> getAllCategories() {
        return recipeCategoryRepository.findAllByOrderByCategoryName();
    }

    public RecipeCategory getCategoryByCategoryId(Long recipeId) {
        return recipeCategoryRepository.findByCategoryId(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Category cannot be found for username: " +recipeId));
    }
}
