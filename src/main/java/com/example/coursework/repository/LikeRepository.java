package com.example.coursework.repository;

import com.example.coursework.entity.Category;
import com.example.coursework.entity.LikeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeModel, Long> {

    List<LikeModel> findAllByOrderByDateLiked();

    Optional<LikeModel> findByLikeId(Long likeId);

    Optional<LikeModel> findByRecipeIdAndUserId(Long likeId, Long userId);

}
