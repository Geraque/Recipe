package com.example.coursework.web;

import com.example.coursework.dto.EnterDTO;
import com.example.coursework.entity.Enter;
import com.example.coursework.facade.CommentFacade;
import com.example.coursework.facade.EnterFacade;
import com.example.coursework.payload.request.LoginRequest;
import com.example.coursework.payload.request.SignupRequest;
import com.example.coursework.payload.response.JWTTokenSuccessResponse;
import com.example.coursework.payload.response.MessageResponse;
import com.example.coursework.security.JWTTokenProvider;
import com.example.coursework.security.SecurityConstants;
import com.example.coursework.services.EnterService;
import com.example.coursework.services.UserService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;
    @Autowired
    private EnterService enterService;
    @Autowired
    private EnterFacade enterFacade;
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        System.out.println(loginRequest.getUsername());
        Enter enter = enterService.saveEnterByEmail(loginRequest.getUsername());
        EnterDTO createdEnter = enterFacade.enterToEnterDTO(enter);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Object> registerAdmin(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createAdmin(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
