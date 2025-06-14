package com.example.timero.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timero.data.local.entity.PostEntity;

import java.util.List;

@Dao
public interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostEntity post);

    @Update
    void update(PostEntity post);

    @Delete
    void delete(PostEntity post);

    @Query("SELECT * FROM posts WHERE id = :postId")
    LiveData<PostEntity> getPostById(int postId);

    @Query("SELECT * FROM posts WHERE userId = :userId")
    LiveData<List<PostEntity>> getPostsByUser(int userId);

    @Query("SELECT * FROM posts ORDER BY createdAt DESC")
    LiveData<List<PostEntity>> getAllPosts();

    @Query("SELECT * FROM posts WHERE category = :category ORDER BY createdAt DESC")
    LiveData<List<PostEntity>> getPostsByCategory(String category);

    @Query("SELECT * FROM posts WHERE title LIKE :searchQuery OR content LIKE :searchQuery")
    LiveData<List<PostEntity>> searchPosts(String searchQuery);
} 