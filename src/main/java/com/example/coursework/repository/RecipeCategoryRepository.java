package com.example.coursework.repository;

import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
    List<RecipeCategory> findAllByOrderByCategoryName();

    Optional<RecipeCategory> findByCategoryId(Long categoryId);

}
