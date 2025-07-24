package com.shopify.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopify.entity.User;
import com.shopify.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserById(id, user));
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserByEmail(email, user));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/created-after/{dateTime}")
    public ResponseEntity<List<User>> getUsersByCreatedAfter(@PathVariable String dateTime) {
        LocalDateTime createdDate = LocalDateTime.parse(dateTime);
        return ResponseEntity.ok(userService.getUsersByCreatedAfter(createdDate));
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    @GetMapping("/exists/phone/{phone}")
    public ResponseEntity<Boolean> existsByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(userService.existsByPhone(phone));
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.existsByUsername(username));
    }
}
