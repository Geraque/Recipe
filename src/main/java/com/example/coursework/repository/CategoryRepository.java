package com.example.coursework.repository;

import com.example.coursework.entity.Category;
import com.example.coursework.entity.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByCategoryName();

    Optional<Category> findByCategoryId(Long categoryId);

}
