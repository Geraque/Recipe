package com.example.coursework.services;

import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.dto.RecipeDTO2;
import com.example.coursework.entity.*;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    public static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final NutritionRepository nutritionRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, ImageRepository imageRepository, RecipeIngredientRepository recipeIngredientRepository, NutritionRepository nutritionRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.nutritionRepository = nutritionRepository;
    }

    public Recipe createRecipe(RecipeDTO recipeDTO, Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        Recipe recipe = new Recipe();
        System.out.println(recipeDTO);
        System.out.println(recipeDTO.getRecipeNutrition());
        recipe.setUser(user);
        recipe.setRecipeName(recipeDTO.getRecipeName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setRecipeNutrition(recipeDTO.getRecipeNutrition());
        recipe.setLikes(0);
        recipeRepository.save(recipe);
        /*
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setQuantity(recipeDTO.getQuantity());
        recipeIngredient.setIngredientId(recipeDTO.getIngredientId());
        System.out.println("ABBBBBBBBBBBBBBOOOOOOOOOOOOOOOOOOOOOOOOOOOBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(recipeRepository.findAllByUserOrderByDateCreatedDesc(user).get(0).getRecipeId());
        recipeIngredient.setRecipeId(recipeRepository.findAllByUserOrderByDateCreatedDesc(user).get(0).getRecipeId());

        recipeIngredientRepository.save(recipeIngredient);
*/
        LOG.info("Saving Recipe for User: {}", user.getEmail());
        return recipe;
    }

    public Recipe createRecipeWithNutrition(RecipeDTO2 recipeDTO, Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        Recipe recipe = new Recipe();
        recipe.setUser(user);
        recipe.setRecipeName(recipeDTO.getRecipeName());
        recipe.setDescription(recipeDTO.getDescription());

        RecipeNutrition recipeNutrition = new RecipeNutrition();
        recipeNutrition.setCalories(recipeDTO.getCalories());
        recipeNutrition.setFat(recipeDTO.getFat());
        recipeNutrition.setProteins(recipeDTO.getProteins());
        recipeNutrition.setCarbs(recipeDTO.getCarbs());

        recipe.setRecipeNutrition(recipeNutrition);
        recipe.setLikes(0);
        recipeRepository.save(recipe);
        /*
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setQuantity(recipeDTO.getQuantity());
        recipeIngredient.setIngredientId(recipeDTO.getIngredientId());
        System.out.println("ABBBBBBBBBBBBBBOOOOOOOOOOOOOOOOOOOOOOOOOOOBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(recipeRepository.findAllByUserOrderByDateCreatedDesc(user).get(0).getRecipeId());
        recipeIngredient.setRecipeId(recipeRepository.findAllByUserOrderByDateCreatedDesc(user).get(0).getRecipeId());

        recipeIngredientRepository.save(recipeIngredient);
*/
        LOG.info("Saving Recipe for User: {}", user.getEmail());
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAllByOrderByDateCreatedDesc();
    }

    public Recipe getRecipeById(Long recipeId, Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        return recipeRepository.findRecipeByRecipeIdAndUser(recipeId, user)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe cannot be found for username: " + user.getEmail()));
    }

    public List<Recipe> getAllRecipeForUser(Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        return recipeRepository.findAllByUserOrderByDateCreatedDesc(user);
    }

    public List<Recipe> getAllRecipeForUsername(String username) {
        UserModel user = getUserByUsername(username);
        return recipeRepository.findAllByUserOrderByDateCreatedDesc(user);
    }

    public Recipe likeRecipe(Long recipeId, String username) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe cannot be found"));

        Optional<String> userLiked = recipe.getLikedUsers()
                .stream()
                .filter(u -> u.equals(username)).findAny();

        if (userLiked.isPresent()) {
            recipe.setLikes(recipe.getLikes() - 1);
            recipe.getLikedUsers().remove(username);
        } else {
            recipe.setLikes(recipe.getLikes() + 1);
            recipe.getLikedUsers().add(username);
        }
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long recipeId, Principal principal) {
        Recipe recipe = getRecipeById(recipeId, principal);
        Optional<ImageModel> imageModel = imageRepository.findByRecipeId(recipe.getRecipeId());
        recipeRepository.delete(recipe);
        imageModel.ifPresent(imageRepository::delete);
    }

    private UserModel getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }

    private UserModel getUserByUsername(String username) {
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }
}
