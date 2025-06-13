package com.example.timero.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timero.R;
import com.example.timero.ui.main.MainActivity;

public class QuestionPostFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button postButton = view.findViewById(R.id.post_questions_button);
        Button addQuestionButton = view.findViewById(R.id.add_question_button);

        addQuestionButton.setOnClickListener(v -> {
            // Logic to add a new question to the RecyclerView will go here
            Toast.makeText(getContext(), "Add Question Clicked!", Toast.LENGTH_SHORT).show();
        });

        postButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Questionnaire posted successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        });
    }
}
