package com.example.ecom.OrderPackage;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 1️⃣ POST /api/orders – Place new order
    @PostMapping
    public Order placeOrder(@RequestParam Long userId, @RequestParam Long cartId) {
        return orderService.placeOrder(userId, cartId);
    }

    // 2️⃣ GET /api/orders/{userId} – Get orders for a logged-in user
    @GetMapping("/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    // 3️⃣ GET /api/admin/orders – Admin: get all orders
    @GetMapping("/admin/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}