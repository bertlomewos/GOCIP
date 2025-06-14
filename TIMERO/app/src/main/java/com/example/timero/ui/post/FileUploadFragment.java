package com.example.timero.ui.post;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;
import com.example.timero.ui.main.MainActivity;

public class FileUploadFragment extends Fragment {

    private PostViewModel postViewModel;
    private TextView fileNameTextView;
    private Uri selectedFileUri;

    private final ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        selectedFileUri = data.getData();
                        String fileName = getFileName(selectedFileUri);
                        fileNameTextView.setText(fileName);
                    }
                }
            });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file_upload, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        fileNameTextView = view.findViewById(R.id.file_name_text);
        LinearLayout uploadArea = view.findViewById(R.id.upload_area);
        Button postButton = view.findViewById(R.id.post_button);

        uploadArea.setOnClickListener(v -> openFilePicker());

        postButton.setOnClickListener(v -> {
            if (selectedFileUri == null) {
                Toast.makeText(getContext(), "Please select a file to upload.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Tell the ViewModel to create and save the post
            postViewModel.createAndSavePost("File");
        });

        // Observe the post creation status
        postViewModel.postCreated.observe(getViewLifecycleOwner(), isCreated -> {
            if (isCreated) {
                Toast.makeText(getContext(), "Post created successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                // Reset the event status
                postViewModel.onPostCreationComplete();
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        String[] mimeTypes = {"video/mp4", "application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        filePickerLauncher.launch(intent);
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
