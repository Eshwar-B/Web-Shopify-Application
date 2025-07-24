package com.shopify.controller;

import com.shopify.entity.Product;
import com.shopify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.saveProduct(product);
        return ResponseEntity.ok(saved);
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Filter by Name
    @GetMapping("/by-name")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    // Filter by Category
    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    // Filter by Price
    @GetMapping("/by-price")
    public ResponseEntity<List<Product>> getProductsByPrice(@RequestParam BigDecimal price) {
        List<Product> products = productService.getProductsByPrice(price);
        return ResponseEntity.ok(products);
    }

    // Filter by Description
    @GetMapping("/by-description")
    public ResponseEntity<List<Product>> getProductsByDescription(@RequestParam String description) {
        List<Product> products = productService.getProductsByDescription(description);
        return ResponseEntity.ok(products);
    }

    // Filter by Image URL
    @GetMapping("/by-image-url")
    public ResponseEntity<List<Product>> getProductsByImageUrl(@RequestParam String imageUrl) {
        List<Product> products = productService.getProductsByImageUrl(imageUrl);
        return ResponseEntity.ok(products);
    }

    // Filter by Created Date
    @GetMapping("/by-created-date")
    public ResponseEntity<List<Product>> getProductsByCreatedDate(@RequestParam String createdDate) {
        LocalDateTime date = LocalDateTime.parse(createdDate);
        List<Product> products = productService.getProductsByCreatedDate(date);
        return ResponseEntity.ok(products);
    }

    // Filter by Updated Date
    @GetMapping("/by-updated-date")
    public ResponseEntity<List<Product>> getProductsByUpdateDate(@RequestParam String updateDate) {
        LocalDateTime date = LocalDateTime.parse(updateDate);
        List<Product> products = productService.getProductsByUpdateDate(date);
        return ResponseEntity.ok(products);
    }
}