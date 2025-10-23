package com.example.Authentication.Controller;

import com.example.Authentication.Entities.Wishlist;
import com.example.Authentication.Service.WishlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @PostMapping
    public Wishlist add(@RequestBody Wishlist item) {
        return service.addToWishlist(item);
    }

    @GetMapping("/{email}")
    public List<Wishlist> get(@PathVariable String email) {
        return service.getUserWishlist(email);
    }

    @DeleteMapping("/{email}/{productId}")
    public void remove(@PathVariable String email, @PathVariable Long productId) {
        service.removeFromWishlist(email, productId);
    }
}
