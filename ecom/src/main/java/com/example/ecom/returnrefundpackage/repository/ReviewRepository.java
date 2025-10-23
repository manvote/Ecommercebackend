package com.example.Authentication.Repositories;

import com.example.Authentication.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserEmail(String userEmail);
}
