package com.example.coursework.repository;

import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeSave;
import com.example.coursework.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeSaveRepository extends JpaRepository<RecipeSave, Long> {

    Optional<RecipeSave> findRecipeSaveByRecipeIdAndUserId(Long recipeId, Long userId);


}
