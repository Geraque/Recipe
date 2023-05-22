package com.example.coursework.services;
import com.example.coursework.entity.Enter;
import com.example.coursework.entity.UserModel;
import com.example.coursework.repository.EnterRepository;
import com.example.coursework.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EnterService {
    public static final Logger LOG = LoggerFactory.getLogger(EnterService.class);

    private final EnterRepository enterRepository;

    private final UserRepository userRepository;

    @Autowired
    public EnterService(EnterRepository enterRepository, UserRepository userRepository) {
        this.enterRepository = enterRepository;
        this.userRepository = userRepository;
    }

    public Enter saveEnter(String username) {
        UserModel user = getUserByUsername(username);
        Enter enter = new Enter();
        enter.setUserId(user.getUserId());

        LOG.info("Saving enter for userId: {}", user.getUserId());
        return enterRepository.save(enter);
    }

    public Enter saveEnterByEmail(String email) {
        UserModel user = getUserByEmail(email);
        Enter enter = new Enter();
        enter.setUserId(user.getUserId());

        LOG.info("Saving enter for userId: {}", user.getUserId());
        return enterRepository.save(enter);
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public UserModel getUserByEmail(String email) {
        return userRepository.findUserModelByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email " + email));
    }
}
