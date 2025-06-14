package com.example.timero.ui.content;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.timero.R;

public class DocumentsFragment extends Fragment {

    private PostContentViewModel viewModel;
    private boolean hasAttemptedOpen = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_documents, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PostContentViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.selectedPost.observe(getViewLifecycleOwner(), post -> {
            if (!hasAttemptedOpen && post != null) {
                String postType = post.getPostType();
                if ("DOCUMENT".equalsIgnoreCase(postType) || "FILE".equalsIgnoreCase(postType)) {
                    hasAttemptedOpen = true;
                    if (post.getImageUrl().startsWith("content://")) {
                        openUserSelectedFile(post.getImageUrl());
                    } else {
                        openWebDocument(post.getImageUrl());
                    }
                }
            }
        });
    }

    private void openUserSelectedFile(String url) {
        Uri fileUri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setData(fileUri);

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No application found to open this file.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openWebDocument(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No application found to open this link.", Toast.LENGTH_SHORT).show();
        }
    }
}