package com.example.timero.ui.main.postcollection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.timero.databinding.FragmentPostCollectionBinding;
import com.example.timero.ui.main.adapter.PostCollectionAdapter;

public class PostCollectionFragment extends Fragment {

    private PostCollectionViewModel viewModel;
    private PostCollectionAdapter adapter;
    private FragmentPostCollectionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostCollectionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PostCollectionViewModel.class);
        setupToolbar();
        setupRecyclerView();
        observeViewModel();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });
    }

    private void setupRecyclerView() {
        binding.postCollectionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostCollectionAdapter();
        binding.postCollectionRecyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.allPosts.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                adapter.setPosts(posts);
            }
        });
    }
}