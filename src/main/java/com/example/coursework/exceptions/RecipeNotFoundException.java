package com.example.coursework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String msg) {
        super(msg);

    }
}