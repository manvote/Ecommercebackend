package com.example.Authentication.Service;

import com.example.Authentication.Entities.Review;
import com.example.Authentication.Repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review addReview(Review review) {
        return repo.save(review);
    }

    public List<Review> getProductReviews(Long productId) {
        return repo.findByProductId(productId);
    }

    public List<Review> getAllReviews() {
        return repo.findAll();
    }

    public Review updateStatus(Long id, String status) {
        Review review = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.status = status;
        return repo.save(review);
    }

    public void deleteReview(Long id) {
        repo.deleteById(id);
    }
}
