package com.example.coursework.services;

import com.example.coursework.dto.UserDTO;
import com.example.coursework.entity.UserModel;
import com.example.coursework.entity.enums.ERole;
import com.example.coursework.exceptions.UserExistException;
import com.example.coursework.payload.request.SignupRequest;
import com.example.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

import static com.example.coursework.entity.enums.ERole.ROLE_ADMIN;
import static com.example.coursework.entity.enums.ERole.ROLE_USER;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel createUser(SignupRequest userIn) {
        UserModel user = new UserModel();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }
    public UserModel createAdmin(SignupRequest userIn) {
        UserModel user = new UserModel();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ROLE_ADMIN);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

    public UserModel updateUser(UserDTO userDTO, Principal principal) {
        UserModel user = getUserByPrincipal(principal);
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());

        return userRepository.save(user);
    }

    public UserModel updateUserByAdmin(UserDTO userDTO) {
        UserModel user = getUserById(userDTO.getUserId());
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());

        return userRepository.save(user);
    }

    public UserModel getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private UserModel getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }

    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public boolean isAdmin(Long userId) {
        UserModel userModel = getUserById(userId);
        String role = "";
        for (ERole e : userModel.getRoles()){
            role = String.valueOf(e);
        }
        if(role.equals("ROLE_USER")){
            return false;
        }
        return true;
    }
}
