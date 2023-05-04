package com.example.coursework.dto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RecipeSaveDTO {
    private Long saveId;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long recipeId;

}
