package com.example.Authentication.Controller;

import com.example.Authentication.Entities.Review;
import com.example.Authentication.Service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    // User adds a review
    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        return service.addReview(review);
    }

    // Get all reviews for a product
    @GetMapping("/reviews/{productId}")
    public List<Review> getProductReviews(@PathVariable Long productId) {
        return service.getProductReviews(productId);
    }

    // Admin: View all reviews
    @GetMapping("/admin/reviews")
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }

    // Admin: Approve / Reject review
    @PutMapping("/admin/reviews/{id}/status")
    public Review updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return service.updateStatus(id, body.get("status"));
    }

    // Admin: Delete review
    @DeleteMapping("/admin/reviews/{id}")
    public void deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
    }
}
