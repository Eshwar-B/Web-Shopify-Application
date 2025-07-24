package com.shopify.service;

import com.shopify.entity.Order;
import com.shopify.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) { return orderRepository.save(order); }

    public Optional<Order> getOrderById(Long id) { return orderRepository.findById(id); }

    public List<Order> getAllOrders() { return orderRepository.findAll(); }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setUserId(updatedOrder.getUserId());
            order.setProductIds(updatedOrder.getProductIds());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            order.setStatus(updatedOrder.getStatus());
            return orderRepository.save(order);
        });
    }

    public void deleteOrder(Long id) { orderRepository.deleteById(id); }

    public List<Order> getOrdersByUserId(Long userId) { return orderRepository.findByUserId(userId); }
    public List<Order> getOrdersByStatus(String status) { return orderRepository.findByStatus(status); }
    public List<Order> getOrdersByProductId(Long productId) { return orderRepository.findByProductIdsContaining(productId); }
    public List<Order> getOrdersByUserIdAndStatus(Long userId, String status) { return orderRepository.findByUserIdAndStatus(userId, status); }
    public List<Order> getOrdersByCreatedAtBetween(LocalDateTime start, LocalDateTime end) { return orderRepository.findByCreatedAtBetween(start, end); }
    public List<Order> getOrdersByCreatedAtAfter(LocalDateTime date) { return orderRepository.findByCreatedAtAfter(date); }
    public List<Order> getOrdersByCreatedAtBefore(LocalDateTime date) { return orderRepository.findByCreatedAtBefore(date); }
    public List<Order> getOrdersByTotalAmountGreaterThanEqual(BigDecimal amount) { return orderRepository.findByTotalAmountGreaterThanEqual(amount); }
    public List<Order> getOrdersByTotalAmountLessThanEqual(BigDecimal amount) { return orderRepository.findByTotalAmountLessThanEqual(amount); }
    public List<Order> getOrdersByTotalAmountBetween(BigDecimal min, BigDecimal max) { return orderRepository.findByTotalAmountBetween(min, max); }
    public List<Order> getOrdersByStatusIn(List<String> statuses) { return orderRepository.findByStatusIn(statuses); }
    public List<Order> getOrdersByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByUserIdAndCreatedAtBetween(userId, start, end);
    }
    public List<Order> getOrdersByProductIdAndStatus(Long productId, String status) {
        return orderRepository.findByProductIdAndStatus(productId, status);
    }
    public List<Order> getRecentOrders(int n) {
        return (n == 10) ? orderRepository.findTop10ByOrderByCreatedAtDesc() : orderRepository.findAll();
    }
    public long countOrdersByStatus(String status) { return orderRepository.countByStatus(status); }
    public long countOrdersByUserId(Long userId) { return orderRepository.countByUserId(userId); }
    public long countOrdersByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return orderRepository.countByCreatedAtBetween(start, end);
    }
}