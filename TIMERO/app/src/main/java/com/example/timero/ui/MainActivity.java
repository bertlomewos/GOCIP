package com.example.timero.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;
import com.example.timero.data.local.TimeroDatabase;
import com.example.timero.data.local.entity.PostEntity;
import com.example.timero.data.local.entity.QuestionEntity;
import com.example.timero.data.local.entity.UserEntity;
import com.example.timero.ui.viewmodel.TimeroViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TimeroViewModel viewModel;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize executor service for database operations
        executorService = Executors.newSingleThreadExecutor();

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(TimeroViewModel.class);

        // Initialize database
        TimeroDatabase database = TimeroDatabase.getInstance(this);
        Log.d(TAG, "Database initialized: " + (database != null));

        // Example: Create and save a user
        UserEntity user = new UserEntity(
            1, // Changed from 0 to 1 as Room doesn't support 0 as primary key
            "testuser",
            "test@example.com",
            "profile.jpg",
            "Test bio",
            0,
            String.valueOf(System.currentTimeMillis()),
            String.valueOf(System.currentTimeMillis())
        );

        // Save user using executor service
        executorService.execute(() -> {
            try {
                viewModel.insertUser(user);
                Log.d(TAG, "User saved successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error saving user: " + e.getMessage());
            }
        });

        // Example: Create and save a post
        PostEntity post = new PostEntity(
            1, // Changed from 0 to 1
            1, // userId
            "Test Post",
            "This is a test post content",
            "post_image.jpg",
            0,
            0,
            String.valueOf(System.currentTimeMillis()),
            String.valueOf(System.currentTimeMillis()),
            "General",
            false
        );

        // Save post using executor service
        executorService.execute(() -> {
            try {
                viewModel.insertPost(post);
                Log.d(TAG, "Post saved successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error saving post: " + e.getMessage());
            }
        });

        // Example: Create and save a question
        QuestionEntity question = new QuestionEntity(
            1, // Changed from 0 to 1
            1, // postId
            1, // userId
            "Test Question",
            "This is a test question content",
            "test,tag",
            0,
            0,
            String.valueOf(System.currentTimeMillis()),
            String.valueOf(System.currentTimeMillis())
        );

        // Save question using executor service
        executorService.execute(() -> {
            try {
                viewModel.insertQuestion(question);
                Log.d(TAG, "Question saved successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error saving question: " + e.getMessage());
            }
        });

        // Observe user data
        viewModel.getUserById(1).observe(this, userEntity -> {
            if (userEntity != null) {
                Log.d(TAG, "User loaded: " + userEntity.getUsername());
                Toast.makeText(this, "User loaded: " + userEntity.getUsername(), Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "User not found");
            }
        });

        // Observe all posts
        viewModel.getAllPosts().observe(this, posts -> {
            if (posts != null) {
                Log.d(TAG, "Number of posts: " + posts.size());
                for (PostEntity postEntity : posts) {
                    Log.d(TAG, "Post: " + postEntity.getTitle());
                }
            } else {
                Log.d(TAG, "No posts found");
            }
        });

        // Observe all questions
        viewModel.getAllQuestions().observe(this, questions -> {
            if (questions != null) {
                Log.d(TAG, "Number of questions: " + questions.size());
                for (QuestionEntity questionEntity : questions) {
                    Log.d(TAG, "Question: " + questionEntity.getTitle());
                }
            } else {
                Log.d(TAG, "No questions found");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
} 