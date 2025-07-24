package com.shopify.repo;

import com.shopify.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByStatus(String status);
    List<Order> findByCreatedAtAfter(LocalDateTime date);
    List<Order> findByCreatedAtBefore(LocalDateTime date);
    List<Order> findByTotalAmountGreaterThanEqual(BigDecimal amount);
    List<Order> findByTotalAmountLessThanEqual(BigDecimal amount);
    List<Order> findByProductIdsContaining(Long productId);
    List<Order> findByUserIdAndStatus(Long userId, String status);
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findTop10ByOrderByCreatedAtDesc();
    List<Order> findByTotalAmountBetween(BigDecimal min, BigDecimal max);
    List<Order> findByStatusIn(List<String> statuses);
    long countByStatus(String status);
    long countByUserId(Long userId);
    long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Order> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT o FROM Order o WHERE :productId IN elements(o.productIds) AND o.status = :status")
    List<Order> findByProductIdAndStatus(Long productId, String status);
}
