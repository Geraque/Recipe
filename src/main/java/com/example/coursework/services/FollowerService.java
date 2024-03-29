package com.example.coursework.services;

import com.example.coursework.entity.Follower;
import com.example.coursework.entity.ImageModel;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.UserModel;
import com.example.coursework.exceptions.RecipeNotFoundException;
import com.example.coursework.repository.FollowerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class FollowerService {
    public static final Logger LOG = LoggerFactory.getLogger(FollowerService.class);

    private final FollowerRepository followerRepository;

    @Autowired
    public FollowerService(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    public Follower followToUser (Long followerId,Long userId) throws IOException {
        Follower follower = new Follower();
        follower.setUserId(userId);
        follower.setFollowingId(followerId);
        return followerRepository.save(follower);
    }

    public void unfollowToUser(Long followerId,Long userId) throws IOException {
        Follower follower = getFollowByFollowerIdAndUserId(followerId, userId);
        followerRepository.delete(follower);
    }

    public boolean isFollowing(Long followerId, Long userId) throws IOException {
        Follower follower = getFollowByFollowerIdAndUserId(followerId, userId);
        return follower != null;
    }

    public Follower getFollowByFollowerIdAndUserId(Long followerId, Long userId) {
        return followerRepository.findByFollowingIdAndUserId(followerId, userId)
                .orElse(null);
    }

    public List<Follower> getFollowerByUserId(Long userId) {
        return followerRepository.findAllByUserId(userId);
    }
    public Long countFollow(Long userId) throws IOException {
        List<Follower> followers = getFollowerByUserId(userId);
        return (long) followers.size();
    }


}
