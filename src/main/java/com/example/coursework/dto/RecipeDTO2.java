package com.example.coursework.dto;

import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.entity.RecipeNutrition;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class RecipeDTO2 {

    private Long recipeId;
    @NotEmpty
    private String recipeName;
    @NotEmpty
    private String description;
    private String username;
    private Long categoryId;
    private Integer calories;

    private Integer proteins;

    private Integer carbs;

    private Integer fat;
    private Integer likes;
    private Set<String> usersLiked;

    private Long ingredientId;

    private Integer quantity;

}
