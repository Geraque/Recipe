package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "likeModel")
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    private Long userId;

    private Long recipeId;

    @Column(updatable = false)
    private LocalDateTime dateLiked;

    public LikeModel() {
    }

    @PrePersist
    protected void onCreate()
    {
        this.dateLiked = LocalDateTime.now();
    }
}
