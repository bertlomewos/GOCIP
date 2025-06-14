package com.example.timero.ui.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.timero.databinding.FragmentQuestionsBinding;
import com.example.timero.data.model.Post;
import com.example.timero.ui.content.adapter.QuestionViewAdapter;

public class QuestionsFragment extends Fragment {

    private PostContentViewModel viewModel;
    private FragmentQuestionsBinding binding;
    private QuestionViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PostContentViewModel.class);
        observeViewModel();
        binding.doneButton.setOnClickListener(v -> {
            if (adapter != null) {
                int score = adapter.calculateScore();
                int totalQuestions = adapter.getItemCount();
                showScoreDialog(score, totalQuestions);
            }
        });
    }

    private void observeViewModel() {
        viewModel.selectedPost.observe(getViewLifecycleOwner(), post -> {
            if (post != null && post.getQuestions() != null && !post.getQuestions().isEmpty()) {
                setupRecyclerView(post);
            } else {
                Toast.makeText(getContext(), "No questions found for this post.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(Post post) {
        adapter = new QuestionViewAdapter(post.getQuestions());
        binding.questionsViewRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.questionsViewRecycler.setAdapter(adapter);
    }

    private void showScoreDialog(int score, int total) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Quiz Complete!")
                .setMessage("You scored " + score + " out of " + total)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}