package com.example.timero.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions",
        foreignKeys = {
            @ForeignKey(entity = PostEntity.class,
                       parentColumns = "id",
                       childColumns = "postId",
                       onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = UserEntity.class,
                       parentColumns = "id",
                       childColumns = "userId",
                       onDelete = ForeignKey.CASCADE)
        })
public class QuestionEntity {
    @PrimaryKey
    private int id;
    private int postId;
    private int userId;
    private String title;
    private String content;
    private String tags;
    private int views;
    private int votes;
    private String createdAt;
    private String updatedAt;

    // Constructor
    public QuestionEntity(int id, int postId, int userId, String title, String content,
                         String tags, int views, int votes, String createdAt, String updatedAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.views = views;
        this.votes = votes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
} 