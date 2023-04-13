package com.example.coursework.dto;

import com.example.coursework.entity.UserModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDTO {

    private long commentId;
    @NotEmpty
    private String commentText;
    private UserModel user;

}
