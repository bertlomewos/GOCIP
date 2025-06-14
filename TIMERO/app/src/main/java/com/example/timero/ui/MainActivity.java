package com.example.timero.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;
import com.example.timero.data.local.entity.PostEntity;
import com.example.timero.data.local.entity.UserEntity;
import com.example.timero.ui.viewmodel.TimeroViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TimeroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(TimeroViewModel.class);

        // Example: Create and save a user
        UserEntity user = new UserEntity(
            0,
            "testuser",
            "test@example.com",
            "profile.jpg",
            "Test bio",
            0,
            String.valueOf(System.currentTimeMillis()),
            String.valueOf(System.currentTimeMillis())
        );
        viewModel.insertUser(user);

        // Example: Create and save a post
        PostEntity post = new PostEntity(
            0,
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
        viewModel.insertPost(post);

        // Observe user data
        viewModel.getUserById(1).observe(this, userEntity -> {
            if (userEntity != null) {
                Log.d(TAG, "User loaded: " + userEntity.getUsername());
                Toast.makeText(this, "User loaded: " + userEntity.getUsername(), Toast.LENGTH_SHORT).show();
            }
        });

        // Observe all posts
        viewModel.getAllPosts().observe(this, posts -> {
            if (posts != null) {
                Log.d(TAG, "Number of posts: " + posts.size());
                for (PostEntity postEntity : posts) {
                    Log.d(TAG, "Post: " + postEntity.getTitle());
                }
            }
        });
    }
} 