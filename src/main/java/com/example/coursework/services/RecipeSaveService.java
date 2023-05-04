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
import java.util.Optional;

@Service
public class RecipeSaveService {
    public static final Logger LOG = LoggerFactory.getLogger(RecipeSaveService.class);

    private final RecipeSaveRepository recipeSaveRepository;
    @Autowired
    public RecipeSaveService(RecipeSaveRepository recipeSaveRepository){
        this.recipeSaveRepository = recipeSaveRepository;
    }

    public RecipeSave saveRecipe(Long userId, Long recipeId) {
        RecipeSave recipeSave = new RecipeSave();
        recipeSave.setUserId(userId);
        recipeSave.setRecipeId(recipeId);
        return recipeSaveRepository.save(recipeSave);
    }

    public void deleteRecipe(Long userId, Long recipeId) {
        RecipeSave recipeSave = getRecipeById(userId, recipeId);
        recipeSaveRepository.delete(recipeSave);
    }

    public RecipeSave getRecipeById(Long userId, Long recipeId) {
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

}
