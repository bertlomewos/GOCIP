package com.example.timero.ui.post;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;
import com.google.android.material.textfield.TextInputEditText;

public class PostDetailFragment extends Fragment {

    private PostViewModel postViewModel;
    private Spinner postTypeSpinner;
    private ImageView postImagePreview;
    private TextInputEditText titleEditText;
    private TextInputEditText descriptionEditText;

    private ActivityResultLauncher<String> mGetContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        postViewModel.setSelectedImageUri(uri);
                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        // Find views
        postImagePreview = view.findViewById(R.id.post_image_preview);
        postTypeSpinner = view.findViewById(R.id.post_type_spinner);
        Button nextButton = view.findViewById(R.id.next_button);
        titleEditText = view.findViewById(R.id.title_edit_text);
        descriptionEditText = view.findViewById(R.id.description_edit_text);

        postImagePreview.setOnClickListener(v -> mGetContent.launch("image/*"));

        nextButton.setOnClickListener(v -> {
            // Explicitly save the data to the ViewModel before navigating
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            postViewModel.title.setValue(title);
            postViewModel.description.setValue(description);

            // Navigate to the next fragment
            String selectedType = postTypeSpinner.getSelectedItem().toString();
            Fragment nextFragment = null;
            if (selectedType.equals("Question")) {
                nextFragment = new QuestionPostFragment();
            } else if (selectedType.equals("File")) {
                nextFragment = new FileUploadFragment();
            }

            if (nextFragment != null && getActivity() instanceof PostActivity) {
                ((PostActivity) getActivity()).loadFragment(nextFragment, true);
            }
        });

        // Observe LiveData to handle screen rotation and image selection
        postViewModel.selectedImageUri.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                postImagePreview.setImageURI(uri);
            }
        });

        // Restore text if it exists in the ViewModel (e.g., after rotation)
        if(postViewModel.title.getValue() != null){
            titleEditText.setText(postViewModel.title.getValue());
        }
        if(postViewModel.description.getValue() != null){
            descriptionEditText.setText(postViewModel.description.getValue());
        }
    }
}
