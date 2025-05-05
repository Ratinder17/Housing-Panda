package com.example.HousingPanda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Listing {

    @Id
    private Long id;

    private String title;
    private String description;
    private Double rent;
    private String address;
    private Integer numberOfRooms;
    private String contactInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Listing() {
        this.title = null;
        this.description = null;
        this.rent = null;
        this.address = null;
        this.numberOfRooms = null;
        this.contactInfo = null;
        this.user = null;
    }

    public Listing(String title, String description, Double rent, String address, Integer numberOfRooms, String contactInfo, User user) {
        this.title = title;
        this.description = description;
        this.rent = rent;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.contactInfo = contactInfo;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
