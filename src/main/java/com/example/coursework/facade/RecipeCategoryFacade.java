package com.example.coursework.facade;

import com.example.coursework.dto.RecipeCategoryDTO;
import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeCategory;
import org.springframework.stereotype.Component;

@Component
public class RecipeCategoryFacade {

    public RecipeCategoryDTO recipeCategoryToRecipeCategoryDTO(RecipeCategory category) {
        RecipeCategoryDTO recipeCategoryDTO = new RecipeCategoryDTO();
        recipeCategoryDTO.setRecipeCategoryId(category.getRecipeCategoryId());
        recipeCategoryDTO.setCategoryId(category.getCategoryId());
        recipeCategoryDTO.setRecipeId(category.getRecipeId());
        return recipeCategoryDTO;
    }

}
