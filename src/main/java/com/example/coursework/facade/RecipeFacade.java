package com.example.coursework.facade;

import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeFacade {

    public RecipeDTO recipeToRecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setUsername(recipe.getUser().getUsername());
        recipeDTO.setRecipeId(recipe.getRecipeId());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setLikes(recipe.getLikes());
        recipeDTO.setUsersLiked(recipe.getLikedUsers());
        recipeDTO.setRecipeName(recipe.getRecipeName());
        recipeDTO.setRecipeNutrition(recipe.getRecipeNutrition());
        return recipeDTO;
    }

}
