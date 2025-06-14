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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timero.R;
import com.example.timero.ui.main.MainActivity;

public class QuestionPostFragment extends Fragment implements QuestionEditAdapter.OnRemoveClickListener, QuestionEditAdapter.OnAddAnswerClickListener {

    private PostViewModel postViewModel;
    private RecyclerView questionsRecyclerView;
    private QuestionEditAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        questionsRecyclerView = view.findViewById(R.id.questions_recycler_view);
        setupRecyclerView();

        Button postButton = view.findViewById(R.id.post_questions_button);
        Button addQuestionButton = view.findViewById(R.id.add_question_button);

        addQuestionButton.setOnClickListener(v -> postViewModel.addQuestion());

        postButton.setOnClickListener(v -> {
            // Tell the ViewModel to create and save the post
            postViewModel.createAndSavePost("Question");
        });

        observeViewModel();
    }

    private void setupRecyclerView() {
        adapter = new QuestionEditAdapter(this, this);
        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        questionsRecyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        postViewModel.questions.observe(getViewLifecycleOwner(), questions -> {
            if (questions != null) {
                adapter.setQuestions(questions);
            }
        });

        // Observe the post creation status
        postViewModel.postCreated.observe(getViewLifecycleOwner(), isCreated -> {
            if (isCreated) {
                Toast.makeText(getContext(), "Questionnaire posted successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                postViewModel.onPostCreationComplete();
            }
        });
    }

    @Override
    public void onRemoveClicked(int position) {
        postViewModel.removeQuestion(position);
    }

    @Override
    public void onAddAnswerClicked(int position) {
        postViewModel.addAnswer(position);
    }
}
