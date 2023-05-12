package com.example.coursework.repository;

import com.example.coursework.entity.Comment;
import com.example.coursework.entity.Enter;
import com.example.coursework.entity.Recipe;
import com.example.coursework.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterRepository extends JpaRepository<Enter, Long> {

    List<Enter> findAllByUserId(Long userId);

}
