package com.example.coursework.web;

import com.example.coursework.dto.CategoryDTO;
import com.example.coursework.dto.RecipeCategoryDTO;
import com.example.coursework.entity.Category;
import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.facade.CategoryFacade;
import com.example.coursework.facade.RecipeCategoryFacade;
import com.example.coursework.services.CategoryService;
import com.example.coursework.services.RecipeCategoryService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> recipeCategoryDTOList = categoryService.getAllCategories()
                .stream()
                .map(categoryFacade::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeCategoryDTOList, HttpStatus.OK);
    }




    @PostMapping("/name/{categoryId}")
    public ResponseEntity<String> getCategoryByCategoryId(@PathVariable("categoryId") String categoryId){
        Category recipeCategory = categoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
        String categoryName = recipeCategory.getCategoryName();

        return new ResponseEntity<>(categoryName, HttpStatus.OK);
    }


}
