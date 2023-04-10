package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_category")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    public RecipeCategory() {
    }

}
