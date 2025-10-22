package com.example.ecom.cartpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add a product to cart
    @PostMapping
    public CartItem addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    // Get all cart items for a user
    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    // Update quantity
    @PutMapping("/{userId}")
    public CartItem updateCartItem(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.updateCartItem(userId, productId, quantity);
    }

    // Remove product from cart
    @DeleteMapping("/{userId}/{productId}")
    public void removeCartItem(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        cartService.removeCartItem(userId, productId);
    }
}