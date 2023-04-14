package com.example.coursework.web;
import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Recipe;
import com.example.coursework.facade.RecipeFacade;
import com.example.coursework.payload.response.MessageResponse;
import com.example.coursework.services.RecipeService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recipe")
@CrossOrigin
public class RecipeController {

    @Autowired
    private RecipeFacade recipeFacade;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO,
                                             BindingResult bindingResult,
                                             Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Recipe recipe = recipeService.createRecipe(recipeDTO, principal);
        RecipeDTO createdRecipe = recipeFacade.recipeToRecipeDTO(recipe);

        return new ResponseEntity<>(createdRecipe, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDTO>> getAllRecipes() {
        List<RecipeDTO> recipeDTOList = recipeService.getAllRecipes()
                .stream()
                .map(recipeFacade::recipeToRecipeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipesForUser(Principal principal) {
        List<RecipeDTO> recipeDTOList = recipeService.getAllRecipeForUser(principal)
                .stream()
                .map(recipeFacade::recipeToRecipeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeDTOList, HttpStatus.OK);
    }

    @PostMapping("/{recipeId}/{username}/like")
    public ResponseEntity<RecipeDTO> likeRecipe(@PathVariable("recipeId") String recipeId,
                                            @PathVariable("username") String username) {
        Recipe recipe = recipeService.likeRecipe(Long.parseLong(recipeId), username);
        RecipeDTO recipeDTO = recipeFacade.recipeToRecipeDTO(recipe);

        return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
    }

    @PostMapping("/{recipeId}/delete")
    public ResponseEntity<MessageResponse> deleteRecipe(@PathVariable("recipeId") String recipeId, Principal principal) {
        recipeService.deleteRecipe(Long.parseLong(recipeId), principal);
        return new ResponseEntity<>(new MessageResponse("Recipe was deleted"), HttpStatus.OK);
    }
}
