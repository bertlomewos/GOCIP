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

import com.example.timero.R;
import com.example.timero.api.RetrofitDatabase;
import com.example.timero.api.postApi;
import com.example.timero.databinding.FragmentHomeBinding;
import com.example.timero.data.model.Post;
import com.example.timero.ui.content.PostContentActivity;
import com.example.timero.ui.main.MainActivity;
import com.example.timero.ui.main.adapter.HotTopicsAdapter;
import com.example.timero.ui.main.adapter.LatestTopicsAdapter;
import com.example.timero.ui.main.postcollection.PostCollectionFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HotTopicsAdapter hotTopicsAdapter;
    private LatestTopicsAdapter latestTopicsAdapter;
    private FragmentHomeBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.moreButton.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_layout, new PostCollectionFragment())
                    .addToBackStack(null)
                    .commit();
        });
        setupHotTopicsRecyclerView();
        setupLatestTopicsRecyclerView();
        observeViewModel();
    }

    private void onPostClicked(Post post) {
        Toast.makeText(getContext(), "Opening: " + post.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), PostContentActivity.class);
        intent.putExtra("POST_EXTRA", post);
        startActivity(intent);
    }

    private void setupHotTopicsRecyclerView() {
        binding.hotTopicsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        hotTopicsAdapter = new HotTopicsAdapter(this::onPostClicked);
        binding.hotTopicsRecyclerView.setAdapter(hotTopicsAdapter);
    }

    private void setupLatestTopicsRecyclerView() {
        binding.latestTopicsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        latestTopicsAdapter = new LatestTopicsAdapter(this::onPostClicked);
        binding.latestTopicsRecyclerView.setAdapter(latestTopicsAdapter);
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