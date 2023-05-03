package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "recipe_ingredient_id", nullable = false)
    private Long recipeIngredientId;
    private Long ingredientId;
    private Long recipeId;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public RecipeIngredient() {
    }
}
