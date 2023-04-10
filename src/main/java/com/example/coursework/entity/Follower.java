package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "follower")
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "follower_id", nullable = false)
    private Long followerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel followingId;

    public Follower() {
    }
}
