package com.example.coursework.repository;

import com.example.coursework.entity.Comment;
import com.example.coursework.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Recipe post);

    Comment findByIdAndUserId(Long commentId, Long userId);

}
