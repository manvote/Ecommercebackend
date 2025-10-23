package com.example.Authentication.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "subcategories")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    public LocalDateTime createdAt = LocalDateTime.now();
}
