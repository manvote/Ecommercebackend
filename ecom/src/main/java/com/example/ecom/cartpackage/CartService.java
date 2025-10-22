package com.example.ecom.cartpackage;

import com.example.ecom.SignUpPackage.Entities.User;
import com.example.ecom.SignUpPackage.Repositories.UserRepository;
import com.example.ecom.productpackage.Product;
import com.example.ecom.productpackage.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get all items in the user's cart
    public List<CartItem> getCartItems(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return cartRepository.findByUser(user);
    }

    // Add a product to cart
    public CartItem addToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        // Check if the product already exists in the user's cart
        return cartRepository.findByUserAndProductId(user, productId)
                .map(existingItem -> {
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    return cartRepository.save(existingItem);
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem(user, product, quantity);
                    return cartRepository.save(newItem);
                });
    }

    // Update quantity
    public CartItem updateCartItem(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow();
        return cartRepository.findByUserAndProductId(user, productId)
                .map(item -> {
                    item.setQuantity(quantity);
                    return cartRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    // Remove item from cart
    public void removeCartItem(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow();
        cartRepository.findByUserAndProductId(user, productId)
                .ifPresent(cartRepository::delete);
    }
}
