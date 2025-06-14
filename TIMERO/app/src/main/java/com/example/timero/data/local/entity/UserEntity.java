package com.example.timero.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    private int id;
    private String username;
    private String email;
    private String profileImage;
    private String bio;
    private int reputation;
    private String createdAt;
    private String updatedAt;

    // Constructor
    public UserEntity(int id, String username, String email, String profileImage, String bio, 
                     int reputation, String createdAt, String updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profileImage = profileImage;
        this.bio = bio;
        this.reputation = reputation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public int getReputation() { return reputation; }
    public void setReputation(int reputation) { this.reputation = reputation; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
} 