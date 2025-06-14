package com.example.timero.ui.content;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.timero.databinding.ActivityPostContentBinding;
import com.example.timero.data.model.Post;

public class PostContentActivity extends AppCompatActivity {

    public static final String POST_EXTRA = "POST_EXTRA";
    private PostContentViewModel viewModel;
    private ActivityPostContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PostContentViewModel.class);

        Post post = getIntent().getParcelableExtra(POST_EXTRA);

        if (post == null) {
            Toast.makeText(this, "Error: Post not found.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        viewModel.setSelectedPost(post);

        if (savedInstanceState == null) {
            Fragment contentFragment;
            String postType = post.getPostType();

            if ("QUESTION".equalsIgnoreCase(postType)) {
                contentFragment = new QuestionsFragment();
            } else if ("VIDEO".equalsIgnoreCase(postType)) {
                contentFragment = new VideoFragment();
            } else if ("DOCUMENT".equalsIgnoreCase(postType) || "FILE".equalsIgnoreCase(postType)) {
                contentFragment = new DocumentsFragment();
            } else {
                Toast.makeText(this, "Unknown post type: " + postType, Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            loadFragment(contentFragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(binding.postContentFrameLayout.getId(), fragment);
            transaction.commit();
        }
    }
}