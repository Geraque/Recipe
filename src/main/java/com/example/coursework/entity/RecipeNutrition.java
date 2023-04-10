package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_nutrition")
public class RecipeNutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "nutrition_id", nullable = false)
    private Long nutritionId;

    private Integer calories;

    private Integer proteins;

    private Integer carbs;

    private Integer fat;

    public RecipeNutrition() {
    }
}
