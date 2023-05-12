package com.example.coursework.services;

import com.example.coursework.entity.*;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeCategoryService {
    public static final Logger LOG = LoggerFactory.getLogger(RecipeCategoryService.class);

    private final RecipeCategoryRepository recipeCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public RecipeCategoryService(RecipeCategoryRepository recipeCategoryRepository, CategoryRepository categoryRepository) {
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategoriesByRecipeId(Long recipeId) {
        List<RecipeCategory> recipeCategories = recipeCategoryRepository.findByRecipeId(recipeId);
        List<Category> categories = new ArrayList<>();
        for(RecipeCategory rcp: recipeCategories){
            Category category = categoryRepository.findByCategoryId(rcp.getCategoryId())
                    .orElseThrow(() -> new RecipeNotFoundException("Category cannot be found for categoryId: " +rcp.getCategoryId()));;
            categories.add(category);
        }
        return categories;
    }

    public RecipeCategory saveRecipeCategory(Long categoryId,Long recipeId) {
        RecipeCategory recipeCategory = new RecipeCategory();
        recipeCategory.setRecipeId(recipeId);
        recipeCategory.setCategoryId(categoryId);
        LOG.info("Saving recipeCategory for recipeId: {}", recipeId);
        return recipeCategoryRepository.save(recipeCategory);
    }


}
