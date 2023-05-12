package com.example.coursework.web;
import com.example.coursework.dto.EnterDTO;
import com.example.coursework.entity.Enter;
import com.example.coursework.facade.EnterFacade;
import com.example.coursework.services.EnterService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/enter")
@CrossOrigin
public class EnterController {

    @Autowired
    private EnterService enterService;
    @Autowired
    private EnterFacade enterFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{username}/create")
    public ResponseEntity<Object> createComment(@PathVariable("username") String username) {
        Enter enter = enterService.saveEnter(username);
        EnterDTO createdEnter = enterFacade.enterToEnterDTO(enter);

        return new ResponseEntity<>(createdEnter, HttpStatus.OK);
    }

}
