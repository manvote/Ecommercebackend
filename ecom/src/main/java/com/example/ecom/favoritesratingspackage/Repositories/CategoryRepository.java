package com.example.Authentication.Repositories;

import com.example.Authentication.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
