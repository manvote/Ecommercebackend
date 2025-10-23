package com.example.Authentication.Controller;

import com.example.Authentication.Entities.Order;
import com.example.Authentication.Entities.OrderItem;
import com.example.Authentication.Repositories.OrderItemRepository;
import com.example.Authentication.Repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderController(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // ✅ Place new order
    @PostMapping
    public Map<String, Object> placeOrder(@RequestBody Map<String, Object> request) {
        String userEmail = request.get("userEmail").toString();
        List<Map<String, Object>> itemsList = (List<Map<String, Object>>) request.get("items");

        Order order = new Order();
        order.userEmail = userEmail;
        order.total = 0.0;
        order.status = "PENDING";
        order = orderRepository.save(order);

        double total = 0;
        for (Map<String, Object> itemData : itemsList) {
            Long productId = Long.valueOf(itemData.get("productId").toString());
            Integer quantity = Integer.valueOf(itemData.get("quantity").toString());
            Double price = Double.valueOf(itemData.get("price").toString());

            OrderItem item = new OrderItem();
            item.productId = productId;
            item.quantity = quantity;
            item.price = price;
            item.order = order;

            total += price * quantity;
            orderItemRepository.save(item);
        }

        order.total = total;
        orderRepository.save(order);

        return Map.of("message", "Order placed successfully", "orderId", order.id);
    }

    // ✅ Get orders for a user
    @GetMapping("/{email}")
    public List<Order> getUserOrders(@PathVariable String email) {
        return orderRepository.findByUserEmail(email);
    }

    // ✅ Admin – View all orders
    @GetMapping("/admin/all")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Update order status (admin only)
    @PutMapping("/{id}/status")
    public Map<String, String> updateStatus(@PathVariable Long id, @RequestParam String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.status = status;
        orderRepository.save(order);
        return Map.of("message", "Order status updated", "status", order.status);
    }
}
