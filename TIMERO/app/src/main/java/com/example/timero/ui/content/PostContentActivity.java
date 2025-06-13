package com.example.timero.ui.content;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.timero.R;
import com.example.timero.data.model.Post;

public class PostContentActivity extends AppCompatActivity {

    public static final String POST_EXTRA = "POST_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        Post post = getIntent().getParcelableExtra(POST_EXTRA);

        if (post == null) {
            Toast.makeText(this, "Error: Post not found.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (savedInstanceState == null) {
            Fragment contentFragment;
            String postType = post.getPostType();

            if ("QUESTION".equalsIgnoreCase(postType)) {
                contentFragment = new QuestionsFragment();
            } else if ("VIDEO".equalsIgnoreCase(postType)) {
                contentFragment = new VideoFragment();
            } else if ("DOCUMENT".equalsIgnoreCase(postType)) {
                contentFragment = new DocumentsFragment();
            } else {
                Toast.makeText(this, "Unknown post type.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Pass the post object to the fragment
            Bundle args = new Bundle();
            args.putParcelable(POST_EXTRA, post);
            contentFragment.setArguments(args);

            // Load the fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.post_content_frame_layout, contentFragment);
            transaction.commit();
        }
    }
}
