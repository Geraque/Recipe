package com.example.coursework.dto;

import com.example.coursework.entity.UserModel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EnterDTO {

    private Long enterId;
    @NotEmpty
    private Long userId;

}
