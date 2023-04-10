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

    @ManyToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Recipe recipe;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public RecipeIngredient() {
    }
}
