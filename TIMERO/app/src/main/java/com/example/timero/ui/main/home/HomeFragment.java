package com.example.timero.ui.main.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timero.R;
import com.example.timero.data.model.Post;
import com.example.timero.ui.content.PostContentActivity;
import com.example.timero.ui.main.adapter.HotTopicsAdapter;
import com.example.timero.ui.main.adapter.LatestTopicsAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HotTopicsAdapter hotTopicsAdapter;
    private LatestTopicsAdapter latestTopicsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setupHotTopicsRecyclerView(view);
        setupLatestTopicsRecyclerView(view);
        observeViewModel();
    }

    /**
     * Handles clicks on any post from either RecyclerView.
     * @param post The post object that was clicked.
     */
    private void onPostClicked(Post post) {
        Toast.makeText(getContext(), "Opening: " + post.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), PostContentActivity.class);
        intent.putExtra("POST_EXTRA", post); // We pass the clicked post object
        startActivity(intent);
    }

    private void setupHotTopicsRecyclerView(View view) {
        RecyclerView hotTopicsRecyclerView = view.findViewById(R.id.hot_topics_recycler_view);
        hotTopicsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hotTopicsAdapter = new HotTopicsAdapter(this::onPostClicked); // Pass the click handler method
        hotTopicsRecyclerView.setAdapter(hotTopicsAdapter);
    }

    private void setupLatestTopicsRecyclerView(View view) {
        RecyclerView latestTopicsRecyclerView = view.findViewById(R.id.latest_topics_recycler_view);
        latestTopicsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        latestTopicsAdapter = new LatestTopicsAdapter(this::onPostClicked); // Pass the click handler method
        latestTopicsRecyclerView.setAdapter(latestTopicsAdapter);
    }

    private void observeViewModel() {
        homeViewModel.hotTopics.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                hotTopicsAdapter.setPosts(posts);
            }
        });

        homeViewModel.latestTopics.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                latestTopicsAdapter.setPosts(posts);
            }
        });
    }
}
