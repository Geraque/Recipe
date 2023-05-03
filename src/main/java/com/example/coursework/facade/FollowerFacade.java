package com.example.coursework.facade;

import com.example.coursework.dto.FollowerDTO;
import com.example.coursework.entity.Follower;
import org.springframework.stereotype.Component;

@Component
public class FollowerFacade {

    public FollowerDTO followerToFollowerDTO(Follower follower) {
        FollowerDTO followerDTO = new FollowerDTO();
        followerDTO.setFollowerId(follower.getFollowerId());
        followerDTO.setUserId(follower.getUserId());
        followerDTO.setFollowingId(follower.getFollowingId());
        return followerDTO;
    }
}
