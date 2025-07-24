package com.shopify.controller;

import com.shopify.entity.Order;
import com.shopify.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Order> getAllOrders() { return orderService.getAllOrders(); }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getByUser(@PathVariable Long userId) { return orderService.getOrdersByUserId(userId); }

    @GetMapping("/status/{status}")
    public List<Order> getByStatus(@PathVariable String status) { return orderService.getOrdersByStatus(status); }

    @GetMapping("/user/{userId}/status/{status}")
    public List<Order> getByUserAndStatus(@PathVariable Long userId, @PathVariable String status) {
        return orderService.getOrdersByUserIdAndStatus(userId, status);
    }

    @GetMapping("/product/{productId}")
    public List<Order> getByProduct(@PathVariable Long productId) { return orderService.getOrdersByProductId(productId); }

    @GetMapping("/product/{productId}/status/{status}")
    public List<Order> getByProductAndStatus(@PathVariable Long productId, @PathVariable String status) {
        return orderService.getOrdersByProductIdAndStatus(productId, status);
    }

    @GetMapping("/statuses")
    public List<Order> getByStatuses(@RequestParam List<String> statuses) {
        return orderService.getOrdersByStatusIn(statuses);
    }

    @GetMapping("/created-between")
    public List<Order> getByCreatedRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return orderService.getOrdersByCreatedAtBetween(start, end);
    }

    @GetMapping("/user/{userId}/created-between")
    public List<Order> getByUserAndDateRange(@PathVariable Long userId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return orderService.getOrdersByUserIdAndCreatedAtBetween(userId, start, end);
    }

    @GetMapping("/created-after")
    public List<Order> getByCreatedAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return orderService.getOrdersByCreatedAtAfter(date);
    }

    @GetMapping("/created-before")
    public List<Order> getByCreatedBefore(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return orderService.getOrdersByCreatedAtBefore(date);
    }

    @GetMapping("/amount-min")
    public List<Order> getByMinAmount(@RequestParam BigDecimal amount) {
        return orderService.getOrdersByTotalAmountGreaterThanEqual(amount);
    }

    @GetMapping("/amount-max")
    public List<Order> getByMaxAmount(@RequestParam BigDecimal amount) {
        return orderService.getOrdersByTotalAmountLessThanEqual(amount);
    }

    @GetMapping("/amount-between")
    public List<Order> getByAmountRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return orderService.getOrdersByTotalAmountBetween(min, max);
    }

    @GetMapping("/recent")
    public List<Order> getRecent(@RequestParam(defaultValue = "10") int n) {
        return orderService.getRecentOrders(n);
    }

    @GetMapping("/count/status/{status}")
    public long countByStatus(@PathVariable String status) {
        return orderService.countOrdersByStatus(status);
    }

    @GetMapping("/count/user/{userId}")
    public long countByUser(@PathVariable Long userId) {
        return orderService.countOrdersByUserId(userId);
    }

    @GetMapping("/count/created-between")
    public long countByDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return orderService.countOrdersByCreatedAtBetween(start, end);
    }
}