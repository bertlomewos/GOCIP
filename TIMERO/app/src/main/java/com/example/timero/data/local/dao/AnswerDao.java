package com.example.timero.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timero.data.local.entity.AnswerEntity;

import java.util.List;

@Dao
public interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AnswerEntity answer);

    @Update
    void update(AnswerEntity answer);

    @Delete
    void delete(AnswerEntity answer);

    @Query("SELECT * FROM answers WHERE id = :answerId")
    LiveData<AnswerEntity> getAnswerById(int answerId);

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    LiveData<List<AnswerEntity>> getAnswersByQuestion(int questionId);

    @Query("SELECT * FROM answers WHERE userId = :userId")
    LiveData<List<AnswerEntity>> getAnswersByUser(int userId);

    @Query("SELECT * FROM answers WHERE isAccepted = 1 AND questionId = :questionId")
    LiveData<AnswerEntity> getAcceptedAnswer(int questionId);

    @Query("SELECT * FROM answers ORDER BY votes DESC")
    LiveData<List<AnswerEntity>> getAllAnswers();
} 