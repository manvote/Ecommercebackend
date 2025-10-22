package com.example.ecom.OrderPackage;



import com.example.ecom.SignUpPackage.Entities.User;
import com.example.ecom.cartpackage.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Simulating external module services (User, Cart)
    // In real app: you'd autowire UserRepository & CartRepository

    @Override
    public Order placeOrder(Long userId, Long cartId) {
        // Step 1️⃣ Create dummy references
        User user = new User(); user.setId(userId);
        CartItem cart = new CartItem(); cart.setId(cartId);

        // Step 2️⃣ Fetch cart items and calculate total (mocked)
        double totalAmount = 1200.00; // Ideally sum(cart.getItems().getPrice() * qty)

        // Step 3️⃣ Create new Order
        Order order = new Order();
        order.setUser(user);
        order.setCart(cart);
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");

        // Step 4️⃣ Save order
        Order savedOrder = orderRepository.save(order);

        // Step 5️⃣ Empty user cart (in real code → call cartService.clearCart(userId))
        System.out.println("Cart emptied for user: " + userId);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}