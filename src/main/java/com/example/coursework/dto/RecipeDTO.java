package com.example.coursework.dto;

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
    private Integer likes;
    private Set<String> usersLiked;

}
