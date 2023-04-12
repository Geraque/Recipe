package com.example.coursework.repository;

import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByUserOrderByDateCreatedDesc(UserModel user);

    List<Recipe> findAllByOrderByDateCreatedDesc();

    Optional<Recipe> findRecipeByRecipeIdAndUser(Long recipeId, UserModel user);

}
