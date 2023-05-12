package com.example.coursework.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RecipeCategoryDTO {
    private Long recipeCategoryId;
    private Long categoryId;
    private Long recipeId;

}
