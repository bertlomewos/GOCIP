package com.example.timero.ui.profile;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timero.R;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.User;
import com.example.timero.ui.content.PostContentActivity;
import com.example.timero.ui.profile.adapter.MyPostsAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private MyPostsAdapter adapter;
    private TextView usernameText, totalViewsText, totalLikesText;
    private CircleImageView profileImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // This will now correctly find ProfileViewModel
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        usernameText = view.findViewById(R.id.username_text);
        totalViewsText = view.findViewById(R.id.total_views_text);
        totalLikesText = view.findViewById(R.id.total_likes_text);
        profileImage = view.findViewById(R.id.profile_image);
        Button editProfileButton = view.findViewById(R.id.edit_profile_button);

        setupRecyclerView(view);
        observeViewModel();

        editProfileButton.setOnClickListener(v -> {
            if (getActivity() instanceof ProfileActivity) {
                ((ProfileActivity) getActivity()).loadFragment(new EditProfileFragment(), true);
            }
        });
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.my_posts_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MyPostsAdapter(post -> {
            // The 'post' variable is now correctly identified as the Post class
            Toast.makeText(getContext(), "Clicked on: " + post.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), PostContentActivity.class);
            // This now works because Post implements Parcelable
            intent.putExtra("POST_EXTRA", post);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.user.observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // All these method calls are now resolved
                usernameText.setText(user.getUsername());
                totalViewsText.setText(user.getTotalViews());
                totalLikesText.setText(user.getTotalLikes());
            }
        });

        viewModel.myPosts.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                adapter.setPosts(posts);
            }
        });
    }
}
