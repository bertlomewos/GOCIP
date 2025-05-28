package com.example.gocip.Activity.PostActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gocip.adapter.QuestionAdapter;
import com.example.gocip.databinding.FragmentPostQuestionsBinding;
import com.example.gocip.model.QuestionItem;


import java.util.ArrayList;
import java.util.List;

public class fragment_post_Questions extends Fragment {

    private FragmentPostQuestionsBinding binding;
    private QuestionAdapter questionAdapter;
    private List<QuestionItem> questionList;

    public fragment_post_Questions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostQuestionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(questionList); // Using QuestionAdapter from Canvas

        // Ensure your FragmentPostQuestionsBinding's XML has a RecyclerView with this ID
        binding.recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewQuestions.setAdapter(questionAdapter);

        // Add an initial question to start with
        if (questionList.isEmpty()) {
            addNewQuestion();
        }

        // Ensure your FragmentPostQuestionsBinding's XML has a Button/FAB with this ID
        binding.buttonAddQuestion.setOnClickListener(v -> addNewQuestion());

        // Ensure your FragmentPostQuestionsBinding's XML has a Button with this ID
        binding.buttonSubmitQuestions.setOnClickListener(v -> submitPost());
    }

    private void addNewQuestion() {
        questionList.add(new QuestionItem()); // Add a new blank question
        questionAdapter.notifyItemInserted(questionList.size() - 1);
        if (questionList.size() > 0) {
            binding.recyclerViewQuestions.smoothScrollToPosition(questionList.size() - 1);
        }
    }

    public void submitPost() {
        // Ensure your FragmentPostQuestionsBinding's XML has an EditText with this ID
        String questionSetTitle = binding.editTextQuestionSetTitle.getText().toString().trim();
        List<QuestionItem> questionsToSubmit = questionAdapter.getQuestionList();

        if (questionsToSubmit.isEmpty()) {
            Toast.makeText(getContext(), "Please add at least one question.", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < questionsToSubmit.size(); i++) {
            QuestionItem q = questionsToSubmit.get(i);
            if (q.getQuestionText().trim().isEmpty()) {
                Toast.makeText(getContext(), "Question #" + (i + 1) + " text cannot be empty.", Toast.LENGTH_SHORT).show();
                binding.recyclerViewQuestions.smoothScrollToPosition(i);
                return;
            }

            int filledOptionsCount = 0;
            for (String option : q.getOptions()) {
                if (!option.trim().isEmpty()) {
                    filledOptionsCount++;
                }
            }
            if (filledOptionsCount < 2) {
                Toast.makeText(getContext(), "Question #" + (i + 1) + " must have at least two options filled.", Toast.LENGTH_SHORT).show();
                binding.recyclerViewQuestions.smoothScrollToPosition(i);
                return;
            }

            if (q.getCorrectOptionIndex() == -1) {
                Toast.makeText(getContext(), "Please select a correct answer for question #" + (i + 1) + ".", Toast.LENGTH_SHORT).show();
                binding.recyclerViewQuestions.smoothScrollToPosition(i);
                return;
            }
        }

        // TODO: Implement your actual submission logic (e.g., save to database, API call)
        StringBuilder submissionDetails = new StringBuilder();
        submissionDetails.append("Question Set Title: ").append(questionSetTitle.isEmpty() ? "N/A" : questionSetTitle).append("\n\n");
        for (int i = 0; i < questionsToSubmit.size(); i++) {
            QuestionItem q = questionsToSubmit.get(i);
            submissionDetails.append("Q").append(i + 1).append(": ").append(q.getQuestionText()).append("\n");
            for (int j = 0; j < q.getOptionsCount(); j++) {
                if (!q.getOption(j).trim().isEmpty()) {
                    submissionDetails.append("  Opt").append(j + 1).append(": ").append(q.getOption(j));
                    if (j == q.getCorrectOptionIndex()) {
                        submissionDetails.append(" (Correct)");
                    }
                    submissionDetails.append("\n");
                }
            }
            submissionDetails.append("\n");
        }

        Toast.makeText(getContext(), "Questions Submitted:\n" + submissionDetails.toString(), Toast.LENGTH_LONG).show();
        clearInputs();
    }

    public void clearInputs() {
        if (binding != null) {
            // Ensure your FragmentPostQuestionsBinding's XML has an EditText with this ID
            binding.editTextQuestionSetTitle.setText("");
            questionList.clear();
            addNewQuestion(); // Add one blank question back
            questionAdapter.notifyDataSetChanged(); // Consider more specific notifications
            binding.editTextQuestionSetTitle.requestFocus();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Important for fragments
    }
}
