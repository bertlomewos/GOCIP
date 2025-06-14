package com.example.timero.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timero.data.local.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM users WHERE id = :userId")
    LiveData<UserEntity> getUserById(int userId);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();

    @Query("SELECT * FROM users WHERE username LIKE :searchQuery")
    LiveData<List<UserEntity>> searchUsers(String searchQuery);
} 