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
        recipeCategoryDTO.setCategoryId(category.getCategoryId());
        recipeCategoryDTO.setCategoryName(category.getCategoryName());
        return recipeCategoryDTO;
    }

}
