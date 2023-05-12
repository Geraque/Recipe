package com.example.coursework.dto;

import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.entity.RecipeNutrition;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class RecipeDTO {

    private Long recipeId;
    @NotEmpty
    private String recipeName;
    @NotEmpty
    private String description;
    private String username;
    private RecipeNutrition recipeNutrition;
    private Integer likes;
    private Set<String> usersLiked;

    private Long ingredientId;

    private Integer quantity;

}
