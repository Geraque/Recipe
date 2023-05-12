package com.example.coursework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "enter")
public class Enter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "enter_id", nullable = false)
    private Long enterId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(updatable = false)
    private LocalDateTime dateEntered;

    public Enter() {
    }

    @PrePersist
    protected void onCreate()
    {
        this.dateEntered = LocalDateTime.now();
    }
}
