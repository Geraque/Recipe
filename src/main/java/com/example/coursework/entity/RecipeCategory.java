package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_category")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long recipeCategoryId;
    private Long recipeId;
    private Long categoryId;

    public RecipeCategory() {
    }

}
