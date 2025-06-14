package com.example.timero.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts",
        foreignKeys = @ForeignKey(entity = UserEntity.class,
                                parentColumns = "id",
                                childColumns = "userId",
                                onDelete = ForeignKey.CASCADE))
public class PostEntity {
    @PrimaryKey
    private int id;
    private int userId;
    private String title;
    private String content;
    private String imageUrl;
    private int likes;
    private int views;
    private String createdAt;
    private String updatedAt;
    private String category;
    private boolean isAnswered;

    // Constructor
    public PostEntity(int id, int userId, String title, String content, String imageUrl,
                     int likes, int views, String createdAt, String updatedAt,
                     String category, boolean isAnswered) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.likes = likes;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.isAnswered = isAnswered;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isAnswered() { return isAnswered; }
    public void setAnswered(boolean answered) { isAnswered = answered; }
} 