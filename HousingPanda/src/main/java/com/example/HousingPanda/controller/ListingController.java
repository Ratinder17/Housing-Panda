package com.example.HousingPanda.controller;

import com.example.HousingPanda.model.Listing;
import com.example.HousingPanda.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/create")
    public ResponseEntity<String> createListing(@RequestBody Listing listing) {
        try {
            if (listing.getUser() == null || listing.getUser().getId() == null) {
                return ResponseEntity.status(400).body("User id missing.");
            }

            Long userId = listing.getUser().getId();

            String s1 = "SELECT COUNT(*) FROM user WHERE id = ?";
            Integer user = jdbcTemplate.queryForObject(s1, Integer.class, userId);

            if (user == null || user == 0) {
                return ResponseEntity.status(400).body("User ID " + userId + " doesn't exist.");
            }

            String s2 = "INSERT INTO listing (title, description, rent, address, number_of_rooms, contact_info, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(s2,
                    listing.getTitle(),
                    listing.getDescription(),
                    listing.getRent(),
                    listing.getAddress(),
                    listing.getNumberOfRooms(),
                    listing.getContactInfo(),
                    userId
            );

            return ResponseEntity.ok("Listing created");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error");
        }
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getListings(@RequestParam(required = false) Long userId) {
        try {
            String s;
            List<Listing> listings;

            if (userId != null) {
                s = "SELECT * FROM listing WHERE user_id = ?";
                listings = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(Listing.class), userId);
                String userSql = "SELECT * FROM user WHERE id = ?";
                for (Listing listing : listings) {
                    User user = jdbcTemplate.queryForObject(userSql, new BeanPropertyRowMapper<>(User.class), userId);
                    listing.setUser(user);
                }
            } else {
                s = "SELECT * FROM listing";
                listings = jdbcTemplate.query(s, new BeanPropertyRowMapper<>(Listing.class));
            }

            if (listings.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(listings);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
