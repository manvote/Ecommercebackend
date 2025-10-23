package com.example.Authentication.Service;

import com.example.Authentication.Entities.Wishlist;
import com.example.Authentication.Repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository repo;

    public WishlistService(WishlistRepository repo) {
        this.repo = repo;
    }

    public Wishlist addToWishlist(Wishlist item) {
        return repo.save(item);
    }

    public List<Wishlist> getUserWishlist(String email) {
        return repo.findByUserEmail(email);
    }

    public void removeFromWishlist(String email, Long productId) {
        repo.deleteByUserEmailAndProductId(email, productId);
    }
}
