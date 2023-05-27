package com.example.coursework.web;
import com.example.coursework.dto.UserDTO;
import com.example.coursework.entity.UserModel;
import com.example.coursework.facade.UserFacade;
import com.example.coursework.services.UserService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        UserModel user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userModelToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId) {
        UserModel user = userService.getUserById(Long.parseLong(userId));
        UserDTO userDTO = userFacade.userModelToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/get/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        UserModel user = userService.getUserByUsername(username);
        UserDTO userDTO = userFacade.userModelToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        UserModel user = userService.updateUser(userDTO, principal);

        UserDTO userUpdated = userFacade.userModelToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/updateByAdmin")
    public ResponseEntity<Object> updateUserByAdmin(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        UserModel user = userService.updateUserByAdmin(userDTO);

        UserDTO userUpdated = userFacade.userModelToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/isAdmin/{userId}")
    public ResponseEntity<Boolean> isAdmin(@PathVariable("userId") String userId) {
        Boolean bool = userService.isAdmin(Long.parseLong(userId));
        return new ResponseEntity<>(bool, HttpStatus.OK);
    }

}