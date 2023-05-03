package com.example.coursework.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FollowerDTO {
    private Long followerId;
    @NotEmpty
    private Long userId;
    @NotEmpty
    private Long followingId;


}
