package com.example.Authentication.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String userEmail;

    @Column(nullable = false)
    public Long productId;

    public LocalDateTime createdAt = LocalDateTime.now();
}
