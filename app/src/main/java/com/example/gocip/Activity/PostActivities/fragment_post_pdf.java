package com.example.gocip.Activity.PostActivities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gocip.R;
import com.example.gocip.databinding.FragmentPostPdfBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_post_pdf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_post_pdf extends Fragment {

    private FragmentPostPdfBinding binding; // Declare binding variable
    private Uri selectedFileUri;

    // ActivityResultLauncher for picking a file
    private final ActivityResultLauncher<Intent> filePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedFileUri = result.getData().getData();
                    if (selectedFileUri != null) {
                        String fileName = getFileName(selectedFileUri);
                        binding.textViewSelectedFileName.setText(fileName != null ? fileName : "File selected");
                        Toast.makeText(getContext(), "File selected: " + (fileName != null ? fileName : selectedFileUri.getPath()), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    public fragment_post_pdf() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostPdfBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonUploadFile.setOnClickListener(v -> openFilePicker());

        binding.buttonSubmitPdf.setOnClickListener(v -> {
            submitPost();
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Allow all file types initially
        // You can be more specific, e.g., "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        String[] mimeTypes = {"application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            filePickerLauncher.launch(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to get file name from Uri
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            try (Cursor cursor = requireContext().getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            } catch (Exception e) {
                // Log error or handle
            }
        }
        if (result == null) {
            result = uri.getPath();
            if (result != null) {
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
        }
        return result;
    }


    public void submitPost() {
        String title = binding.editTextPdfTitle.getText().toString().trim();
        String description = binding.editTextPdfDescription.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please fill title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedFileUri == null) {
            Toast.makeText(getContext(), "Please select a file", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement your file upload and submission logic
        Toast.makeText(getContext(), "PDF Lesson Submitted:\nTitle: " + title + "\nFile: " + binding.textViewSelectedFileName.getText(), Toast.LENGTH_LONG).show();
        clearInputs();
    }

    public void clearInputs() {
        if (binding != null) {
            binding.editTextPdfTitle.setText("");
            binding.editTextPdfDescription.setText("");
            binding.textViewSelectedFileName.setText("No file selected");
            selectedFileUri = null;
            binding.editTextPdfTitle.requestFocus();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}