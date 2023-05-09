package com.example.coursework.web;
import com.example.coursework.dto.RecipeCategoryDTO;
import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.RecipeCategory;
import com.example.coursework.facade.RecipeCategoryFacade;
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
public class RecipeCategoryController {

    @Autowired
    private RecipeCategoryFacade recipeCategoryFacade;
    @Autowired
    private RecipeCategoryService recipeCategoryService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;


    @GetMapping("/all")
    public ResponseEntity<List<RecipeCategoryDTO>> getAllCategories() {
        List<RecipeCategoryDTO> recipeCategoryDTOList = recipeCategoryService.getAllCategories()
                .stream()
                .map(recipeCategoryFacade::recipeCategoryToRecipeCategoryDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeCategoryDTOList, HttpStatus.OK);
    }

    @PostMapping("/name/{categoryId}")
    public ResponseEntity<String> getCategoryByCategoryId(@PathVariable("categoryId") String categoryId){
        RecipeCategory recipeCategory = recipeCategoryService.getCategoryByCategoryId(Long.parseLong(categoryId));
        String categoryName = recipeCategory.getCategoryName();

        return new ResponseEntity<>(categoryName, HttpStatus.OK);
    }
}
