package com.example.coursework.web;

import com.example.coursework.dto.FollowerDTO;
import com.example.coursework.entity.Follower;
import com.example.coursework.facade.FollowerFacade;
import com.example.coursework.payload.response.MessageResponse;
import com.example.coursework.services.FollowerService;
import com.example.coursework.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/follower")
@CrossOrigin
public class FollowerController {


    @Autowired
    private FollowerService followerService;
    @Autowired
    private FollowerFacade followerFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/follow/{followerId}/{userId}")
    public ResponseEntity<FollowerDTO> followToUser(@PathVariable("followerId") String followerId,
                                                     @PathVariable("userId") Long userId) throws IOException {
        Follower follower = followerService.followToUser(Long.parseLong(followerId), userId);
        FollowerDTO followerDTO = followerFacade.followerToFollowerDTO(follower);

        return new ResponseEntity<>(followerDTO, HttpStatus.OK);
    }

    @PostMapping("/unfollow/{followerId}/{userId}")
    public ResponseEntity<MessageResponse> unfollowToUser(@PathVariable("followerId") String followerId,
                                                          @PathVariable("userId") Long userId) throws IOException {
        followerService.unfollowToUser(Long.parseLong(followerId), userId);
        return new ResponseEntity<>(new MessageResponse("Follower was deleted"), HttpStatus.OK);
    }

    @PostMapping("/isFollowing/{followerId}/{userId}")
    public ResponseEntity<Boolean> isFollowing(@PathVariable("followerId") String followerId,
                                               @PathVariable("userId") Long userId) throws IOException {
        boolean check = followerService.isFollowing(Long.parseLong(followerId), userId);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }

    @PostMapping("/countFollow/{userId}")
    public ResponseEntity<Long> countFollow(@PathVariable("userId") Long userId) throws IOException {
        Long countFollow = followerService.countFollow(userId);
        return new ResponseEntity<>(countFollow, HttpStatus.OK);
    }
}
