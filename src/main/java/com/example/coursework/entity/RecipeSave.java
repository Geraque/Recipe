package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_save")
public class RecipeSave {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "save_id", nullable = false)
    private Long saveId;
    private Long userId;
    private Long recipeId;
}
