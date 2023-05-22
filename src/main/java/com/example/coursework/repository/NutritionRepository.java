package com.example.coursework.repository;

import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeNutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutritionRepository extends JpaRepository<RecipeNutrition, Long> {
    Optional<RecipeNutrition> findRecipeNutritionByNutritionId(Long nutritionId);
}
