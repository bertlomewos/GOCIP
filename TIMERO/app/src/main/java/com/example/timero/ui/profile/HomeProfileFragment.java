package com.example.timero.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.timero.databinding.FragmentHomeProfileBinding;
import com.example.timero.data.model.Post;
import com.example.timero.ui.content.PostContentActivity;
import com.example.timero.ui.profile.adapter.MyPostsAdapter;

public class HomeProfileFragment extends Fragment {

    private ProfileViewModel viewModel;
    private MyPostsAdapter adapter;
    private FragmentHomeProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        setupRecyclerView();
        observeViewModel();
        binding.editProfileButton.setOnClickListener(v -> {
            if (getActivity() instanceof ProfileActivity) {
                ((ProfileActivity) getActivity()).loadFragment(new EditProfileFragment(), true);
            }
        });
    }

    private void setupRecyclerView() {
        binding.myPostsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new MyPostsAdapter(post -> {
            Toast.makeText(getContext(), "Clicked on: " + post.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), PostContentActivity.class);
            intent.putExtra("POST_EXTRA", post);
            startActivity(intent);
        });
        binding.myPostsRecyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.user.observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                binding.usernameText.setText(user.getUsername());
                binding.totalViewsText.setText(user.getTotalViews());
                binding.totalLikesText.setText(user.getTotalLikes());
            }
        });
        viewModel.myPosts.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                adapter.setPosts(posts);
            }
        });
    }
}