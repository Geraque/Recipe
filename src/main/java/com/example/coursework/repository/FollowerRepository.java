package com.example.coursework.repository;

import com.example.coursework.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Optional<Follower> findByFollowingIdAndUserId(Long followingId,Long userId);

    List<Follower> findAllByUserId(Long userId);
}
