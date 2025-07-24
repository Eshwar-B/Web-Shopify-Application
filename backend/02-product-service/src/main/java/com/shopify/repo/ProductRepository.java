package com.shopify.repo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopify.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByCategory(String category);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByDescription(String description);

    List<Product> findByImageUrl(String imageUrl);

    List<Product> findByCreatedDate(LocalDateTime createdDate);

    List<Product> findByUpdateDate(LocalDateTime updateDate);
}
