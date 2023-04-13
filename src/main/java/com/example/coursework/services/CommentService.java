package com.example.coursework.services;
import com.example.coursework.dto.CommentDTO;
import com.example.coursework.entity.Comment;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.UserModel;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.CommentRepository;
import com.example.coursework.repository.RecipeRepository;
import com.example.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    public static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public Comment saveComment(Long recipeId, CommentDTO commentDTO, Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe cannot be found for username: " + user.getEmail()));

        Comment comment = new Comment();
        comment.setRecipe(recipe);
        comment.setUser(user);
        comment.setCommentText(commentDTO.getCommentText());

        LOG.info("Saving comment for Recipe: {}", recipe.getRecipeId());
        return commentRepository.save(comment);
    }

    public List<Comment> getAllCommentsForRecipe(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe cannot be found"));
        List<Comment> comments = commentRepository.findAllByRecipe(recipe);

        return comments;
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.ifPresent(commentRepository::delete);
    }


    private UserModel getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }
}
