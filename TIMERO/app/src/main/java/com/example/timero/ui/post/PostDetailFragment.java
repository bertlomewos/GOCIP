package com.example.timero.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.timero.R;

public class PostDetailFragment extends Fragment {

    private PostViewModel postViewModel;
    private Spinner postTypeSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        postTypeSpinner = view.findViewById(R.id.post_type_spinner);
        Button nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {
            String selectedType = postTypeSpinner.getSelectedItem().toString();

            Fragment nextFragment = null;
            if (selectedType.equals("Question")) {
                nextFragment = new QuestionPostFragment();
            } else if (selectedType.equals("Document") || selectedType.equals("Video")) {
                nextFragment = new FileUploadFragment();
            }

            if (nextFragment != null && getActivity() instanceof PostActivity) {
                ((PostActivity) getActivity()).loadFragment(nextFragment, true);
            }
        });
    }
}
