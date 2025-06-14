package com.example.timero.data.local.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers",
        foreignKeys = {
            @ForeignKey(entity = QuestionEntity.class,
                       parentColumns = "id",
                       childColumns = "questionId",
                       onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = UserEntity.class,
                       parentColumns = "id",
                       childColumns = "userId",
                       onDelete = ForeignKey.CASCADE)
        })
public class AnswerEntity {
    @PrimaryKey
    private int id;
    private int questionId;
    private int userId;
    private String content;
    private int votes;
    private boolean isAccepted;
    private String createdAt;
    private String updatedAt;

    // Constructor
    public AnswerEntity(int id, int questionId, int userId, String content,
                       int votes, boolean isAccepted, String createdAt, String updatedAt) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.content = content;
        this.votes = votes;
        this.isAccepted = isAccepted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }

    public boolean isAccepted() { return isAccepted; }
    public void setAccepted(boolean accepted) { isAccepted = accepted; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
} 