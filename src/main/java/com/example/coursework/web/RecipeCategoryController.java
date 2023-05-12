package com.example.coursework.web;

import com.example.coursework.entity.Category;
import com.example.coursework.facade.RecipeCategoryFacade;
import com.example.coursework.services.RecipeCategoryService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipeCategory")
@CrossOrigin
public class RecipeCategoryController {

    @Autowired
    private RecipeCategoryFacade recipeCategoryFacade;
    @Autowired
    private RecipeCategoryService recipeCategoryService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

/*
    @GetMapping("/all")
    public ResponseEntity<List<RecipeCategoryDTO>> getAllCategories() {
        List<RecipeCategoryDTO> recipeCategoryDTOList = recipeCategoryService.getAllCategories()
                .stream()
                .map(recipeCategoryFacade::recipeCategoryToRecipeCategoryDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeCategoryDTOList, HttpStatus.OK);
    }

 */

    @PostMapping("/all/{recipeId}")
    public ResponseEntity<List<Category>> getCategoryByCategoryId(@PathVariable("recipeId") String recipeId){

        List<Category> categories = recipeCategoryService.getCategoriesByRecipeId(Long.parseLong(recipeId));

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


}
