package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient_category")
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ingredient_category_id", nullable = false)
    private Long ingredientCategoryId;

    @Column(name = "ingredient_category_name", nullable = false)
    private String ingredientCategoryName;

    public IngredientCategory() {
    }
}
