package com.example.timero.ui.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timero.R;
import com.example.timero.data.model.Post;
import com.example.timero.ui.content.adapter.QuestionViewAdapter;

public class QuestionsFragment extends Fragment {

    private PostContentViewModel viewModel;
    private RecyclerView recyclerView;
    private QuestionViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the shared ViewModel from the parent Activity
        viewModel = new ViewModelProvider(requireActivity()).get(PostContentViewModel.class);

        recyclerView = view.findViewById(R.id.questions_view_recycler);
        Button doneButton = view.findViewById(R.id.done_button);

        observeViewModel();

        doneButton.setOnClickListener(v -> {
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void showScoreDialog(int score, int total) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Quiz Complete!")
                .setMessage("You scored " + score + " out of " + total)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
