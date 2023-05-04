package com.example.coursework.facade;

import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.dto.RecipeSaveDTO;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeSave;
import org.springframework.stereotype.Component;

@Component
public class RecipeSaveFacade {

    public RecipeSaveDTO recipeSaveToRecipeSaveDTO(RecipeSave recipeSave) {
        RecipeSaveDTO recipeSaveDTO = new RecipeSaveDTO();
        recipeSaveDTO.setSaveId(recipeSave.getSaveId());
        recipeSaveDTO.setUserId(recipeSave.getUserId());
        recipeSaveDTO.setRecipeId(recipeSave.getRecipeId());
        return recipeSaveDTO;
    }

}
