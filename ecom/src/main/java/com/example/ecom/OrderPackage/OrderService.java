package com.example.ecom.OrderPackage;




import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, Long cartId);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
}