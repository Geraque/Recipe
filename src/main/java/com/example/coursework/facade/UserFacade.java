package com.example.coursework.facade;

import com.example.coursework.dto.UserDTO;
import com.example.coursework.entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO userModelToUserDTO(UserModel user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstname(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

}
