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
import androidx.recyclerview.widget.RecyclerView;
import com.example.timero.R;
import com.example.timero.ui.main.adapter.PostCollectionAdapter;
import com.google.android.material.appbar.MaterialToolbar;

public class PostCollectionFragment extends Fragment {

    private PostCollectionViewModel viewModel;
    private PostCollectionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PostCollectionViewModel.class);

        setupToolbar(view);
        setupRecyclerView(view);
        observeViewModel();

        // Load the posts for a dummy topic
        viewModel.loadPostsForTopic("dummy_id");
    }

    private void setupToolbar(View view) {
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            // Go back to the previous fragment (HomeFragment)
            getParentFragmentManager().popBackStack();
        });
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.post_collection_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostCollectionAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.posts.observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                adapter.setPosts(posts);
            }
        });
    }
}
