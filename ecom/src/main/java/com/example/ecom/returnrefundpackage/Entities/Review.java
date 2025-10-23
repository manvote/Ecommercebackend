package com.example.Authentication.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public Long productId;

    @Column(nullable = false)
    public String userEmail;

    @Column(nullable = false)
    public int rating;

    public String comment;
    public String status = "PENDING";
    public LocalDateTime createdAt = LocalDateTime.now();
}
