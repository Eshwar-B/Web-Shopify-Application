package com.shopify.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopify.entity.Product;
import com.shopify.repo.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setImageUrl(product.getImageUrl());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //  Filter methods returning lists
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByPrice(BigDecimal price) {
        return productRepository.findByPrice(price);
    }

    public List<Product> getProductsByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    public List<Product> getProductsByImageUrl(String imageUrl) {
        return productRepository.findByImageUrl(imageUrl);
    }

    public List<Product> getProductsByCreatedDate(LocalDateTime createdDate) {
        return productRepository.findByCreatedDate(createdDate);
    }

    public List<Product> getProductsByUpdateDate(LocalDateTime updateDate) {
        return productRepository.findByUpdateDate(updateDate);
    }
    
    
}
