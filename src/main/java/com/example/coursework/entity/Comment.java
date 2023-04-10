package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Recipe recipe;
    @Column(columnDefinition = "text", nullable = false)
    private String commentText;

    @Column(updatable = false)
    private LocalDateTime dateCommented;

    public Comment() {
    }

    @PrePersist
    protected void onCreate()
    {
        this.dateCommented = LocalDateTime.now();
    }
}
