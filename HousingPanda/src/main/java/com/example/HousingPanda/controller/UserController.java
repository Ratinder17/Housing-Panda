package com.example.HousingPanda.controller;

import com.example.HousingPanda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String sql = "INSERT INTO user (name, email, phone) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhone());
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
