package com.example.gocip.Activity.PostActivities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gocip.R;
import com.example.gocip.databinding.FragmentPostYoutubeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_post_youtube#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_post_youtube extends Fragment {

    private FragmentPostYoutubeBinding binding; // Declare binding variable

    public fragment_post_youtube() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentPostYoutubeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up listeners or any initial logic here
        binding.buttonSubmitYoutube.setOnClickListener(v -> {
            submitPost();
        });
    }

    // Public method to be called from Activity to submit
    public void submitPost() {
        String title = binding.editTextYoutubeTitle.getText().toString().trim();
        String url = binding.editTextYoutubeUrl.getText().toString().trim();
        String description = binding.editTextYoutubeDescription.getText().toString().trim();

        if (title.isEmpty() || url.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement your submission logic (e.g., save to database, API call)
        Toast.makeText(getContext(), "YouTube Lesson Submitted:\nTitle: " + title + "\nURL: " + url, Toast.LENGTH_LONG).show();
        clearInputs(); // Optionally clear inputs after submission
    }

    // Public method to be called from Activity to clear inputs
    public void clearInputs() {
        if (binding != null) {
            binding.editTextYoutubeTitle.setText("");
            binding.editTextYoutubeUrl.setText("");
            binding.editTextYoutubeDescription.setText("");
            binding.editTextYoutubeTitle.requestFocus(); // Focus on the first field
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Important to nullify the binding object in Fragments
    }
}