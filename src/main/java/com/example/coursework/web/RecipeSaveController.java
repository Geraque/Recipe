package com.example.coursework.web;
import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.dto.RecipeSaveDTO;
import com.example.coursework.entity.RecipeSave;
import com.example.coursework.facade.RecipeFacade;
import com.example.coursework.facade.RecipeSaveFacade;
import com.example.coursework.payload.response.MessageResponse;
import com.example.coursework.services.RecipeSaveService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recipeSave")
@CrossOrigin("*")
public class RecipeSaveController {

    @Autowired
    private RecipeSaveFacade recipeSaveFacade;

    @Autowired
    private RecipeFacade recipeFacade;
    @Autowired
    private RecipeSaveService recipeSaveService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{userId}/recipes")
    public ResponseEntity<List<RecipeDTO>> getAllRecipeForUserId(@PathVariable("userId") String userId) {
        List<RecipeDTO> recipeDTOList = recipeSaveService.getAllRecipeForUserId(Long.valueOf(userId))
                .stream()
                .map(recipeFacade::recipeToRecipeDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(recipeDTOList, HttpStatus.OK);
    }

    @PostMapping("/save/{userId}/{recipeId}")
    public ResponseEntity<RecipeSaveDTO> saveRecipe(@PathVariable("userId") Long userId,
                                                    @PathVariable("recipeId") Long recipeId) {
        RecipeSave recipeSave = recipeSaveService.saveRecipe(userId, recipeId);
        RecipeSaveDTO recipeSaveDTO = recipeSaveFacade.recipeSaveToRecipeSaveDTO(recipeSave);

        return new ResponseEntity<>(recipeSaveDTO, HttpStatus.OK);
    }
    @PostMapping("/delete/{userId}/{recipeId}")
    public ResponseEntity<MessageResponse> deleteRecipe(@PathVariable("userId") Long userId,
                                                    @PathVariable("recipeId") Long recipeId) {
        recipeSaveService.deleteRecipe(userId, recipeId);
        return new ResponseEntity<>(new MessageResponse("Recipe was deleted"), HttpStatus.OK);
    }

    @PostMapping("/isSaved/{userId}/{recipeId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable("userId") Long userId,
                                               @PathVariable("recipeId") Long recipeId) throws IOException {
        boolean check = recipeSaveService.isSaved(userId, recipeId);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

}
