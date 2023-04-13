package com.example.coursework.web;
import com.example.coursework.entity.ImageModel;
import com.example.coursework.payload.response.MessageResponse;
import com.example.coursework.services.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("api/image")
@CrossOrigin
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file, principal);
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @PostMapping("/{recipeId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToRecipe(@PathVariable("recipeId") String recipeId,
                                                             @RequestParam("file") MultipartFile file,
                                                             Principal principal) throws IOException {
        imageUploadService.uploadImageToRecipe(file, principal, Long.parseLong(recipeId));
        return ResponseEntity.ok(new MessageResponse("Image Uploaded Successfully"));
    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageForUser(Principal principal) {
        ImageModel userImage = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{recipeId}/image")
    public ResponseEntity<ImageModel> getImageToRecipe(@PathVariable("recipeId") String recipeId) {
        ImageModel recipeImage = imageUploadService.getImageToRecipe(Long.parseLong(recipeId));
        return new ResponseEntity<>(recipeImage, HttpStatus.OK);
    }

}

