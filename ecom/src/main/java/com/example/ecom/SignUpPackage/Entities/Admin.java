package com.example.ecom.SignUpPackage.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(unique = true)
    public String email;

    @Column(name = "username", nullable = false)
    public String name;

    public String password;

    public LocalDateTime createdAt = LocalDateTime.now();
}
