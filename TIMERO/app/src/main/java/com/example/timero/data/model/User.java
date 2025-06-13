package com.example.timero.data.model;

public class User {
    private String username;
    private String totalViews;
    private String totalLikes;
    private String profilePictureUrl;

    public User(String username, String totalViews, String totalLikes, String profilePictureUrl) {
        this.username = username;
        this.totalViews = totalViews;
        this.totalLikes = totalLikes;
        this.profilePictureUrl = profilePictureUrl;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public String getTotalLikes() {
        return totalLikes;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
}
