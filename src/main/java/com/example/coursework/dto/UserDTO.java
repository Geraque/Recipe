package com.example.coursework.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    private Long userId;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    private String username;
}
