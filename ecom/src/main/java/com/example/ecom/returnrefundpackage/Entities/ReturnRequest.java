package com.example.Authentication.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "returns")
public class ReturnRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public Long orderId;

    @Column(nullable = false)
    public String userEmail;

    public String reason;
    public String status = "PENDING";
    public LocalDateTime createdAt = LocalDateTime.now();
}
