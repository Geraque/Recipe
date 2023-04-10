package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @Column(updatable = false)
    private LocalDateTime dateLiked;

    public Like() {
    }

    @PrePersist
    protected void onCreate()
    {
        this.dateLiked = LocalDateTime.now();
    }
}
