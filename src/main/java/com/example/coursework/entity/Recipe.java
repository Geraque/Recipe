package com.example.coursework.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;
    @Column(name = "recipe_name", nullable = false)
    private String recipeName;

    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    private RecipeCategory recipeCategory;

    @OneToOne(fetch = FetchType.LAZY)
    private RecipeNutrition recipeNutrition;

    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "description", nullable = false)
    private String description;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers = new HashSet<>();

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "recipe", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    public Recipe() {
    }

    @PrePersist
    protected void onCreate()
    {
        this.dateCreated = LocalDateTime.now();
    }
}
