package com.example.coursework.services;

import com.example.coursework.entity.Category;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    public static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByCategoryName();
    }



    public Category getCategoryByCategoryId(Long recipeId) {
        return categoryRepository.findByCategoryId(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Category cannot be found for username: " +recipeId));
    }


}
