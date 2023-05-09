package com.example.coursework.services;

import com.example.coursework.entity.*;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.exceptions.RecipeSaveNotFoundException;
import com.example.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeSaveService {
    public static final Logger LOG = LoggerFactory.getLogger(RecipeSaveService.class);
    private final RecipeRepository recipeRepository;
    private final RecipeSaveRepository recipeSaveRepository;
    @Autowired
    public RecipeSaveService(RecipeRepository recipeRepository, RecipeSaveRepository recipeSaveRepository){
        this.recipeRepository = recipeRepository;
        this.recipeSaveRepository = recipeSaveRepository;
    }


    public List<Recipe> getAllRecipeForUserId(Long userId) {
        List<RecipeSave> recipesId =  recipeSaveRepository.findAllRecipeIdByUserId(userId);
        List<Recipe> recipes = new ArrayList<>();
        for(RecipeSave recipeSave: recipesId){
            recipes.add(getRecipeById(recipeSave.getRecipeId()));
        }
        return recipes;
    }

    public RecipeSave saveRecipe(Long userId, Long recipeId) {
        RecipeSave recipeSave = new RecipeSave();
        recipeSave.setUserId(userId);
        recipeSave.setRecipeId(recipeId);
        return recipeSaveRepository.save(recipeSave);
    }

    public void deleteRecipe(Long userId, Long recipeId) {
        RecipeSave recipeSave = getRecipeSaveById(userId, recipeId);
        recipeSaveRepository.delete(recipeSave);
    }

    public RecipeSave getRecipeSaveById(Long userId, Long recipeId) {
        return recipeSaveRepository.findRecipeSaveByRecipeIdAndUserId(recipeId, userId)
                .orElseThrow(() -> new RecipeSaveNotFoundException("Recipe cannot be found for userId: " + userId));
    }

    public boolean isSaved(Long userId, Long recipeId) throws IOException {
        RecipeSave recipeSave = getRecipeSaveByUserIdAndRecipeId(userId, recipeId);
        return recipeSave != null;
    }

    public RecipeSave getRecipeSaveByUserIdAndRecipeId(Long userId, Long recipeId) {
        return recipeSaveRepository.findRecipeSaveByRecipeIdAndUserId(recipeId, userId)
                .orElse(null);
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findRecipeByRecipeId(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe cannot be found for username: " + recipeId));
    }
}
