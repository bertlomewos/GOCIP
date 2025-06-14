package com.example.timero.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timero.data.local.entity.QuestionEntity;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuestionEntity question);

    @Update
    void update(QuestionEntity question);

    @Delete
    void delete(QuestionEntity question);

    @Query("SELECT * FROM questions WHERE id = :questionId")
    LiveData<QuestionEntity> getQuestionById(int questionId);

    @Query("SELECT * FROM questions WHERE userId = :userId")
    LiveData<List<QuestionEntity>> getQuestionsByUser(int userId);

    @Query("SELECT * FROM questions WHERE postId = :postId")
    LiveData<List<QuestionEntity>> getQuestionsByPost(int postId);

    @Query("SELECT * FROM questions ORDER BY createdAt DESC")
    LiveData<List<QuestionEntity>> getAllQuestions();

    @Query("SELECT * FROM questions WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    LiveData<List<QuestionEntity>> searchQuestions(String searchQuery);

    @Query("SELECT * FROM questions WHERE tags LIKE :tag")
    LiveData<List<QuestionEntity>> getQuestionsByTag(String tag);
} 