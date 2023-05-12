package com.example.coursework.web;

import com.example.coursework.dto.EnterDTO;
import com.example.coursework.dto.RecipeCategoryDTO;
import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Category;
import com.example.coursework.entity.Enter;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.facade.RecipeCategoryFacade;
import com.example.coursework.services.RecipeCategoryService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
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

    @PostMapping("/all/{recipeId}")
    public ResponseEntity<List<Category>> getCategoryByCategoryId(@PathVariable("recipeId") String recipeId){

        List<Category> categories = recipeCategoryService.getCategoriesByRecipeId(Long.parseLong(recipeId));

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add/{categoryId}/{recipeId}")
    public ResponseEntity<Object> saveRecipeCategory(@PathVariable("categoryId") String categoryId,
                                                            @PathVariable("recipeId") String recipeId){
        RecipeCategory recipeCategory = recipeCategoryService.saveRecipeCategory(Long.parseLong(categoryId),Long.parseLong(recipeId));
        RecipeCategoryDTO recipeCategoryDTO = recipeCategoryFacade.recipeCategoryToRecipeCategoryDTO(recipeCategory);

        return new ResponseEntity<>(recipeCategoryDTO, HttpStatus.OK);
    }


}
