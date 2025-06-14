package com.example.timero.ui.post;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.databinding.FragmentPostDetailBinding;

public class PostDetailFragment extends Fragment {

    private PostViewModel postViewModel;
    private FragmentPostDetailBinding binding;
    private ActivityResultLauncher<String[]> mGetContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGetContent = registerForActivityResult(new ActivityResultContracts.OpenDocument(),
                uri -> {
                    if (uri != null) {
                        final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
                        requireContext().getContentResolver().takePersistableUriPermission(uri, takeFlags);
                        postViewModel.setSelectedImageUri(uri);
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        // ✅ Launch file picker
        binding.postImagePreview.setOnClickListener(v -> mGetContent.launch(new String[]{"image/*"}));

        // ✅ Save post data and go to next fragment
        binding.nextButton.setOnClickListener(v -> {
            String title = binding.titleEditText.getText().toString();
            String description = binding.descriptionEditText.getText().toString();

            postViewModel.title.setValue(title);
            postViewModel.description.setValue(description);

            String selectedType = binding.postTypeSpinner.getSelectedItem().toString();
            Fragment nextFragment = null;

            if (selectedType.equals("Question")) {
                nextFragment = new QuestionPostFragment();
            } else if (selectedType.equals("Video") || selectedType.equals("Document")) {
                nextFragment = new FileUploadFragment();
            }

            if (nextFragment != null && getActivity() instanceof PostActivity) {
                ((PostActivity) getActivity()).loadFragment(nextFragment, true);
            }
        });

        // ✅ Observe and preview selected image
        postViewModel.selectedImageUri.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                binding.postImagePreview.setImageURI(uri);
            }
        });

        // ✅ Restore previous input if available
        if (postViewModel.title.getValue() != null) {
            binding.titleEditText.setText(postViewModel.title.getValue());
        }
        if (postViewModel.description.getValue() != null) {
            binding.descriptionEditText.setText(postViewModel.description.getValue());
        }
    }
}
