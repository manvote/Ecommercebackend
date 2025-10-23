package com.example.Authentication.Repositories;

import com.example.Authentication.Entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findByCategoryId(Long categoryId);
}
